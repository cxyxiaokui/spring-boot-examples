package cn.lijunkui.cache;

import cn.lijunkui.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.io.*;


@Service
public class JedisCacheService extends JedisCacheServiceSupport  {

    private static Logger logger = LoggerFactory.getLogger(JedisCacheService.class);



    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取缓存中的对象
     * @param key
     * @return
     */
    public Object getObject(String key) {
        Jedis jedis = null;
        Object object = null;
        try {
            jedis = jedisPool.getResource();
            byte[] ObjectByteArray = jedis.get(key.getBytes());
            object = unserialize(ObjectByteArray);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(jedis);
        }
        return object;
    }

    /**
     *  将对象缓存到Redis中，设置默认过期时间
     * @param key
     * @param value
     */
    public void putObject(String key, Object value) {
        putObject(key,value,null);
    }
    /**
     *  将对象缓存到Redis中，自定义认过期时间
     * @param key
     * @param value
     */
    public void putObject(String key, Object value, Long expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.psetex(key.getBytes(),getExpireTime(expireTime),serialize(value));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(jedis);
        }
    }


    /**
     * 序列化
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            IOUtil.closeStream(oos);
            IOUtil.closeStream(baos);
        }
        return null;
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        if (bytes == null) return null;

        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            IOUtil.closeStream(bais);
            IOUtil.closeStream(ois);
        }
        return null;
    }
}
