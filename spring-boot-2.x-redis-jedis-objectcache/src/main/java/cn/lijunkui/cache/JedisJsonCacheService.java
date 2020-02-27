package cn.lijunkui.cache;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Service
public class JedisJsonCacheService extends JedisCacheServiceSupport {

    private static Logger logger = LoggerFactory.getLogger(JedisJsonCacheService.class);

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取缓存中的对象
     * @param key
     * @param clazz
     * @return
     */
    public Object getObject(String key,Class clazz) {
        Jedis jedis = null;
        Object object = null;
        try {
            jedis = jedisPool.getResource();
            String objectJson = jedis.get(key);
             object = toObjce(objectJson,clazz);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(jedis);
        }
        return object;
    }

    /**
     * 将对象缓存到Redis中，设置默认过期时间
     * @param key
     * @param value
     */
    public void putObject(String key, Object value) {
        putObject(key, value,null);
    }

    /**
     * 将对象缓存到Redis中，自定义认过期时间
     * @param key
     * @param value
     * @param expireTime
     */
    public void putObject(String key, Object value, Long expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.psetex(key,getExpireTime(expireTime),toJson(value));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(jedis);
        }
    }



    /**
     * 将对象转换成Json串
     * @param value
     * @return
     */
    private String toJson(Object value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    /**
     * 将Json串转换成对象
     * @param json
     * @param clazz
     * @return
     */
    private Object  toObjce(String json,Class clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json,clazz);
    }
}
