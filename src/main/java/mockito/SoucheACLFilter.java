package mockito;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.souche.optimus.common.config.OptimusConfig;
import com.souche.optimus.common.util.EnvUtil;
import com.souche.optimus.common.util.Exceptions;
import com.souche.optimus.remoting.metrics.profiler.ApplicationInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Activate(group = Constants.PROVIDER)
public class SoucheACLFilter implements Filter {

    private SoucheACLFilterHelper soucheACLFilterHelper;

    private JedisPool jedisPool;

    private String domain;

    private Boolean needWork = true;

    private Jedis jedis;

    private String appName;

    public SoucheACLFilter() {
        init();
    }

    /**
     * 初始化 acl跨域调用规则信息
     */
    private void init() {
        Boolean soucheEnable = OptimusConfig.getValue("dubbo.acl.souche.enable", Boolean.class, true);
        if (!soucheEnable) {
            log.info("[dubbo acl] not work because [dubbo.acl.souche.enable] is {}", soucheEnable);
            needWork = false;
            return;
        }
        Boolean testEnable = OptimusConfig.getValue("dubbo.acl.testenv.enable", Boolean.class, false);
        if (!testEnable) {
            if (!(EnvUtil.isPrepubEnv() || EnvUtil.isProEnv())) {
                log.info("[dubbo acl] not work in other than prepub or pro env");
                needWork = false;
                return;
            }
        }
        domain = EnvUtil.getM_sdomain();
        if (StringUtils.isBlank(domain) || StringUtils.equalsIgnoreCase("default", domain)) {
            log.info("[dubbo acl] not work because domain is {}", domain);
            needWork = false;
            return;
        }
        domain = domain.toLowerCase().trim();
        appName = ApplicationInfo.NAME;
        if (StringUtils.isBlank(appName)) {
            appName = OptimusConfig.getValue("app.id");
        }
        if (StringUtils.isBlank(appName)) {
            appName = OptimusConfig.getValue("systemInfo.appName");
        }
        if (StringUtils.isBlank(appName)) {
            appName = OptimusConfig.getValue("dubbo.application");
        }
        String url = OptimusConfig.getValue("dubbo.acl.redis.url");
        jedisPool = new JedisPool(url);

        try {
            jedis = jedisPool.getResource();

            soucheACLFilterHelper = new SoucheACLFilterHelper(jedis, appName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Exceptions.fault(e);
        }
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (!needWork) {
            log.debug("[dubbo acl] SoucheACLFilter need not to work, go on invoking");
            return invoker.invoke(invocation);
        }
        String serviceName = invocation.getAttachment("interface");
        String capp = invocation.getAttachment("_capp");
        String cdomain = invocation.getAttachment(Constants.CDOMAIN);
        if (StringUtils.isBlank(cdomain)) {
            log.info("[dubbo acl] there isn't attachment _cdomain, go on invoking");
            return invoker.invoke(invocation);
        } else if (cdomain.equalsIgnoreCase("default")) {
            log.info("[dubbo acl] not to work, because cdomain is default");
            return invoker.invoke(invocation);
        } else {
            cdomain = cdomain.trim();
        }
        if (StringUtils.isBlank(capp)) {
            log.info("[dubbo acl] there isn't attachment _capp, go on invoking");
            return invoker.invoke(invocation);
        } else {
            capp = capp.toLowerCase().trim();
        }
        if (cdomain.equalsIgnoreCase(domain)) {
            log.debug("[dubbo acl] This call is from the same domain:{}, serviceName:{}", cdomain, serviceName);
            return invoker.invoke(invocation);
        }
        if (StringUtils.isBlank(serviceName)) {
            throw new IllegalArgumentException("interface can't be empty");
        }
        if (!checkFromTree(serviceName, capp, cdomain)) {
            log.error("[dubbo acl] checkFromTree return false, serviceName:{}, capp:{}, cdomain:{}", serviceName, capp, cdomain);
            throw new RpcException("the invoke is invalid：[dubbo acl] checkFromTree return false, serviceName:=>>>" + serviceName + ", capp:=>>>"
                    + capp + ", cdomain:=>>>" + cdomain + " acl:=>>>" + soucheACLFilterHelper.getJedisAclMap() + ", sapp:=>>>" + appName);
        }
        return invoker.invoke(invocation);
    }

    /**
     * @param serviceName
     * @param capp
     * @param cdomain
     * @return
     */
    private Boolean checkFromTree(String serviceName, String capp, String cdomain) {
        cleanCacheAndReload();
        String makeMcKey = makeMcKey(serviceName, capp, cdomain);
        Boolean checkFromTree = cacheMap.get(makeMcKey);
        if (checkFromTree != null) {
            return checkFromTree;
        }
        synchronized (cacheMap) {
            if (cacheMap.get(makeMcKey) == null) {
                checkFromTree = soucheACLFilterHelper.checkFromTree(serviceName, capp, cdomain);
                cacheMap.put(makeMcKey, checkFromTree);
            }
        }
        return checkFromTree;
    }

    /**
     * @param serviceName
     * @param capp
     * @param cdomain
     * @return
     */
    private String makeMcKey(String serviceName, String capp, String cdomain) {
        return serviceName + "&" + capp + "&" + cdomain;
    }

    /**
     * 缓存cache
     */
    private Map<String, Boolean> cacheMap = new ConcurrentHashMap<String, Boolean>();

    /**
     * cacheMap超时触发器，清空本地缓存，重新从redis load 数据。
     */
    private void cleanCacheAndReload() {
        // 重制
        if (System.currentTimeMillis() - lastUpTime > 60*1000) { // 最多60秒重制一次
            soucheACLFilterHelper.buildRoot();
            cacheMap.clear();
            lastUpTime = System.currentTimeMillis();
            log.info("buildRoot lastUpTime:{}", lastUpTime);
        }
    }

    long lastUpTime = 0L;

}