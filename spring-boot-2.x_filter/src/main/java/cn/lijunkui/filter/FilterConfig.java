package cn.lijunkui.filter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean<Filter> filterRegistration(){
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(new URLFilter());
		List<String> urlList = new ArrayList<String>();
		urlList.add("/*");
		filterRegistrationBean.setUrlPatterns(urlList);
		filterRegistrationBean.setName("URLFilter");
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}
}
