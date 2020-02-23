package cn.lijunkui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
    Logger log = LoggerFactory.getLogger(RedisTemplateTest.class);

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void set(){
        redisTemplate.opsForValue().set("a","1",30, TimeUnit.MINUTES);
    }
    @Test
    public void get(){
        String value =  redisTemplate.opsForValue().get("a");
        log.info("key 为 a 的值是：{}",value);
    }
    @Test
    public void del(){
        redisTemplate.delete("a");
    }


}
