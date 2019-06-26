package cn.lijunkui.jedis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConditionalOnClass(JedisPool.class)
@EnableConfigurationProperties(JedisProperties.class)
public class JedisAutoConfiguration {
	private JedisProperties jedisProperties;
	
	public JedisAutoConfiguration(JedisProperties jedisProperties) {
		this.jedisProperties = jedisProperties;
	}
	
	@Bean
	@ConditionalOnMissingBean(JedisPool.class)
	public JedisPool jedisPool() {
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(jedisProperties.getPool().getMaxIdle());
		poolConfig.setMaxTotal(jedisProperties.getPool().getMaxTotal());
		poolConfig.setMaxWaitMillis(jedisProperties.getPool().getMaxWaitMillis() * 1000);
		
		JedisPool jedisPool = new JedisPool(poolConfig, jedisProperties.getHost(), jedisProperties.getPort(),
				jedisProperties.getTimeout()*1000, jedisProperties.getPassword(), 0);
		return jedisPool;
	}
}
