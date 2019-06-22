package cn.lijunkui.jedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisTest {
	
	@Autowired
	private JedisPool jedisPool;
	@Test
	public void set() {
		Jedis jedis = jedisPool.getResource();
		jedis.set("abc", "123");
	}
	
	@Test
	public void get() {
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get("abc");
		System.out.println(value);
	}
}
