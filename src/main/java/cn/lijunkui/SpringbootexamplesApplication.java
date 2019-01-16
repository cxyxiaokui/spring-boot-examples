package cn.lijunkui;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class SpringbootexamplesApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringbootexamplesApplication.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	public static void main(String[] args) {
		SpringApplication.run(SpringbootexamplesApplication.class, args);
		 log.info("reportCurrentTimeInitialDelay fixedRate The time is start {}", dateFormat.format(new Date()));
	}
}
