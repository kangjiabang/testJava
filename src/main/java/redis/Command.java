package redis;

/**
 * @Author：zeqi
 * @Date: Created in 14:11 23/4/18.
 * @Description:
 */
public enum Command {

    /* 字符串相关操作 */
    GET, MGET, GETSET, APPEND, SET, MSET, SETNX, SETEX, STRLEN,

    /* 计数相关操作 */
    DECRBY, DECR, INCRBY, INCR,

    /* Hash结构相关操作 */
    HSET, HGET, HSCAN, HSETNX, HMSET, HMGET, HINCRBY, HEXISTS, HDEL, HLEN, HKEYS, HVALS, HGETALL,

    /* List结构相关操作 */
    LPUSH, LPUSHX, LLEN, LRANGE, LTRIM, LINDEX, LSET, LREM, LPOP, RPUSH, RPUSHX, RPOP, RPOPLPUSH, BLPOP, BRPOP, BRPOPLPUSH,

    /* Set结构相关操作 */
    SADD, SMEMBERS, SSCAN, SREM, SPOP, SMOVE, SCARD, SISMEMBER, SINTER, SINTERSTORE, SUNION, SUNIONSTORE, SDIFF, SDIFFSTORE, SRANDMEMBER,

    /* 有序集合 */
    ZADD, ZRANGE, ZSCAN, ZREM, ZINCRBY, ZRANK, ZREVRANK, ZREVRANGE, ZCARD, ZSCORE,
    //
    ZCOUNT, ZRANGEBYSCORE, ZREVRANGEBYSCORE, ZREMRANGEBYRANK, ZREMRANGEBYSCORE, ZUNIONSTORE, ZINTERSTORE, ZLEXCOUNT, ZRANGEBYLEX, ZREMRANGEBYLEX,

    /* bitmap相关 */
    SETBIT, GETBIT, BITCOUNT, BITOP,

    /* hyperLogLog */
    PFADD, PFCOUNT, PFMERGE,

    /* 发布订阅 */
    SUBSCRIBE, PUBLISH, UNSUBSCRIBE, PSUBSCRIBE, PUNSUBSCRIBE, PUBSUB,

    /* 执行lua脚本，在server端是原子操作 */
    EVAL, EVALSHA, SCRIPT,

    /* Sentinel相关 */
    SENTINEL,

    /* 通用，运维相关 */
    MIGRATE, DEL, PING, TTL, PTTL, QUIT, EXISTS, TYPE, SCAN, KEYS, RANDOMKEY, RENAME, RENAMENX, RENAMEX, DBSIZE, EXPIRE, PEXPIRE, EXPIREAT, PEXPIREAT,
    //
    SELECT, MOVE, SAVE, BGSAVE, BGREWRITEAOF, LASTSAVE, CLIENT, TIME, INFO, MONITOR, SLAVEOF, CONFIG, SYNC, PERSIST, ECHO, LINSERT, SLOWLOG,

    //geo
    GEOADD, GEODIST, GEOHASH, GEOPOS, GEORADIUS, GEORADIUSBYMEMBER,

    //cluster
    CLUSTER, ASKING;

    //public final byte[] raw;

    Command() {
        //raw = Codec.encode(this.name());
    }

    public boolean isWriteCommand() {
        switch (this) {
            //
            case SET:
                return true;
            case SETEX:
                return true;
            case SETNX:
                return true;
            case GETSET:
                return true;
            //
            case LPUSH:
                return true;
            case LPUSHX:
                return true;
            case RPUSH:
                return true;
            case RPUSHX:
                return true;
            case RPOPLPUSH:
                return true;
            case BRPOPLPUSH:
                return true;
            //
            case SADD:
                return true;
            case ZADD:
                return true;
            //
            case HSET:
                return true;
            case HMSET:
                return true;
            //
            case EVAL:
                return true;

            default:
                return false;
        }
    }
}