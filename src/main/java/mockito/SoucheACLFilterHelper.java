package mockito;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.souche.optimus.common.util.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class SoucheACLFilterHelper {

    /** spring.RedisTemplate的序列化 */
    private static final GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

    @Getter
    private Map<String, String> jedisAclMap;

    private Jedis jedis;

    private String redisAclKey;
    private String appName;

    @Getter
    @Setter
    @AllArgsConstructor
    public class ACLEntity {
        private String name;
        private int depth;
        private Set<String> appDomainSet;
        private List<ACLEntity> next;
    }

    @Setter
    private ACLEntity root;

    public SoucheACLFilterHelper(Jedis jedis, String appName) {
        this.jedis = jedis;
        this.appName = appName;
        makeRedisAclKey(appName);
        buildRoot();
    }

    @Synchronized
    public void buildRoot() {
        makeJedisAclMap();
        this.root = buildTree();
    }

    private void makeRedisAclKey(String appName) {
        this.redisAclKey = "acl:dubbo:" + appName;
    }

    public ACLEntity buildTree() {
        Map<String, String> map = this.jedisAclMap;
        if (CollectionUtils.isEmpty(map)) {
            log.info("[dubbo acl] map from redis is empty, tree return null");
            return null;
        }
        ACLEntity root = new ACLEntity("zroot", -1, null, null);
        ACLEntity parent = root;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String serviceName = entry.getKey();
            if (StringUtils.isBlank(serviceName)) {
                log.warn("[dubbo acl] serviceName from map is empty, map is {}", map);
                continue;
            }
            String[] names = serviceName.trim().split("\\.");
            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                List<ACLEntity> nexts = parent.getNext();
                if (nexts == null) {
                    List<ACLEntity> list = Lists.newLinkedList();
                    ACLEntity aclEntity;
                    if (i == names.length - 1) {
                        aclEntity = new ACLEntity(name, i, convert2String(entry.getValue()), null);
                    } else {
                        aclEntity = new ACLEntity(name, i, null, null);
                    }
                    list.add(aclEntity);
                    parent.setNext(list);
                    parent = aclEntity;
                } else {
                    boolean find = false;
                    for (ACLEntity entity : nexts) {
                        if (entity.getName().equals(name)) {
                            if (i == names.length - 1) {
                                Set<String> appDomainSet = entity.getAppDomainSet();
                                if (appDomainSet == null) {
                                    entity.setAppDomainSet(convert2String(entry.getValue()));
                                } else {
                                    appDomainSet.addAll(convert2String(entry.getValue()));
                                }
                            } else {
                                parent = entity;
                            }
                            find = true;
                            break;
                        }
                    }
                    if (!find) {
                        ACLEntity aclEntity = null;
                        if (i == names.length - 1) {
                            aclEntity = new ACLEntity(name, i, convert2String(entry.getValue()), null);
                        } else {
                            aclEntity = new ACLEntity(name, i, null, null);
                            parent = aclEntity;
                        }
                        /**
                         * 确保节点*在最前面
                         */
                        if ("*".equalsIgnoreCase(name)) {
                            nexts.add(0, aclEntity);
                        } else {
                            nexts.add(aclEntity);
                        }
                    }
                }
            }
            parent = root;
        }
        return root;
    }

    @Setter
    @Getter
    private static class ConsumerInfo {
        private String code;
        private String domain;
    }

    private Set<String> convert2String(String consumerInfoStr) {

        ArrayList deserialize = genericJackson2JsonRedisSerializer.deserialize(consumerInfoStr.getBytes(), ArrayList.class);
        Set<String> info = Sets.newHashSet();
        if (CollectionUtils.isEmpty(deserialize)) {
            return info;
        }
        try {
            List<ConsumerInfo> consumerInfos = JSON.parseArray(deserialize.toString(), ConsumerInfo.class);
            if (consumerInfos != null) {
                for (ConsumerInfo consumerInfo : consumerInfos) {
                    info.add(consumerInfo.getDomain() + "#" + consumerInfo.getCode());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return info;
    }

    public Boolean checkFromTree(String service, String cApp, String cDomain) {
        if (root == null) {
            log.warn("[dubbo acl] root is null!!");
            return false;
        }
        String[] sns = service.split("\\.");
        ACLEntity parent = root;
        for (int i = 0; i < sns.length; i++) {
            List<ACLEntity> nexts = parent.getNext();
            if (nexts == null) {
                log.warn("[dubbo acl] parent's nexts is null, parent is {}", parent);
                return false;
            }
            for (ACLEntity next : nexts) {
                if (next == null) {
                    log.warn("[dubbo acl] nexts is null, parent is {}", parent);
                    return false;
                }
                if ("*".equalsIgnoreCase(next.getName()) && next.getAppDomainSet() != null && next.getNext() == null) {
                    Set<String> set = next.getAppDomainSet();
                    if (isMatchDomainAndApp(cApp, cDomain, set)) {
                        return true;
                    }
                } else if (sns[i].equalsIgnoreCase(next.getName()) && i == next.getDepth()) {
                    parent = next;
                    if (i == sns.length - 1) {
                        if (next.getAppDomainSet() != null) {
                            Set<String> set = next.getAppDomainSet();
                            if (isMatchDomainAndApp(cApp, cDomain, set)) {
                                return true;
                            }
                        } else if (next.getNext() != null) {
                            /**
                             * 看下一层是否有*, 比如service是com.souche.v3, 看树中是否有com.souche.v3.*
                             */
                            List<ACLEntity> tmpEntities = next.getNext();
                            if (tmpEntities != null) {
                                for (ACLEntity entity : tmpEntities) {
                                    if ("*".equalsIgnoreCase(entity.getName()) && entity.getNext() == null && entity.getAppDomainSet() != null) {
                                        Set<String> set = entity.getAppDomainSet();
                                        if (isMatchDomainAndApp(cApp, cDomain, set)) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        return false;
    }

    /**
     * 是否匹配domain 和 set 信息
     * @param cApp
     * @param cDomain
     * @param set
     * @return
     */
    private boolean isMatchDomainAndApp(String cApp, String cDomain, Set<String> set) {
        return set.contains(cDomain + "#" + cApp) || set.contains(cDomain + "#*") || set.contains("*#*");
    }

    public void makeJedisAclMap() {
        try {
            this.jedisAclMap = jedis.hgetAll(redisAclKey);
            log.info("reload {} acl", appName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}