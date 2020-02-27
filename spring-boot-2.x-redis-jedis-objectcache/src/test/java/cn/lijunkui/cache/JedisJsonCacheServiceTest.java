package cn.lijunkui.cache;

import cn.lijunkui.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisJsonCacheServiceTest {

    private Logger logger = LoggerFactory.getLogger(JedisJsonCacheServiceTest.class);
    @Autowired
    private JedisJsonCacheService jedisJsonCacheService;

    @Test
    public void putObject() {
        User user = new User("zhuoqiammingyue2",20);
        jedisJsonCacheService.putObject("user02",user);
        logger.info("缓存用户成功！");
    }

    @Test
    public void getObject() {
        User user = (User)jedisJsonCacheService.getObject("user02",User.class);
        logger.info("User name={},age={}",user.getName(),user.getAge());
    }
}