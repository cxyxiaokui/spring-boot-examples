package cn.lijunkui.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.lijunkui.servlet.UserServlet;

@Configuration
public class WebApplicationConfig {
	
	@Bean
	public ServletRegistrationBean<UserServlet> userServlet(){
		return new ServletRegistrationBean<UserServlet>(new UserServlet());
	}
}
