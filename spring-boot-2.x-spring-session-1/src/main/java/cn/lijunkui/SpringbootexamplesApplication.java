package cn.lijunkui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
//@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.ON_SAVE)
public class SpringbootexamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootexamplesApplication.class, args);
	}
}
