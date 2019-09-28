package cn.lijunkui.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.lijunkui.listener.ApplicationListener;

@Configuration
public class WebApplicationConfig {
	@Bean
	public ServletListenerRegistrationBean<ApplicationListener>  userServlet(){
		return new ServletListenerRegistrationBean<ApplicationListener> (new ApplicationListener());
	}
}
