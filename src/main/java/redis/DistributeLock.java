package redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @Author：zeqi
 * @Date: Created in 15:59 2/4/18.
 * @Description:
 */
public class DistributeLock {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;
    static Jedis jedis = null;

    static {
        jedis = new Jedis("localhost", 6379);
    }

    public static boolean lock(String lockKey,String requestId,int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }


    public static boolean unLock(String lockKey,String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

}
