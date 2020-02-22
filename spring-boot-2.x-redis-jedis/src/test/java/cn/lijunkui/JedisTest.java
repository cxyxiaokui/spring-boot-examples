package cn.lijunkui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisTest {
    Logger log = LoggerFactory.getLogger(JedisTest.class);
    @Autowired
    private JedisPool jedisPool;

    @Test
    public void set() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String returnVlaue = jedis.set("a","1");
            log.info("set 返回值是：{}",returnVlaue);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
    @Test
    public void get(){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String returnVlaue = jedis.get("a");
            log.info("key 为 a的值是：{}",returnVlaue);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    @Test
    public void del(){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long count = jedis.del("a");
            log.info("删除 key 为 a 的值");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
}
