package cn.lijunkui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.lijunkui.enable.EnableHttpClient;

@SpringBootApplication
@EnableHttpClient()
public class SpringbootexamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootexamplesApplication.class, args);
	}
}
