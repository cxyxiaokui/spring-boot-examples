package cn.lijunkui.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.lijunkui.listener.AnnotationFilter;

@Configuration
public class WebApplicationConfig {
	@Bean
	public FilterRegistrationBean<AnnotationFilter>  userServlet(){
		return new FilterRegistrationBean<AnnotationFilter>(new AnnotationFilter());
	}
}
