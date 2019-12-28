package cn.zhuoqianmingyue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.lijunkui.autoconfig.EnableHttpClient;

@SpringBootApplication
@EnableHttpClient
public class SpringBootExamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExamplesApplication.class, args);
	}
}
