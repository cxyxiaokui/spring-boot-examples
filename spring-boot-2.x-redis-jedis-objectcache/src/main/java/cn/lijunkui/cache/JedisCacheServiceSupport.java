package cn.lijunkui.cache;

import redis.clients.jedis.Jedis;

public abstract class JedisCacheServiceSupport {
    public static final long EXPIRE_MILLISECONDS_DEFAULT_LONG = 3*60*60*1000;

    public Long getExpireTime(Long expireTime) {
        expireTime = (expireTime == null || expireTime.longValue() <= 0) ? EXPIRE_MILLISECONDS_DEFAULT_LONG : expireTime;
        return expireTime;
    }

    public void close(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }
}
