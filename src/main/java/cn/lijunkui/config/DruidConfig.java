package cn.lijunkui.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;

@Configuration
public class DruidConfig {
	@ConfigurationProperties(prefix="spring.druid")
	@Bean(initMethod="init",destroyMethod="close")
	public DruidDataSource dataSource() throws SQLException{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setProxyFilters(Lists.newArrayList(statFilter()));
		return dataSource;
	}
	
	@Bean
	public Filter statFilter(){
		StatFilter filter = new StatFilter();
		filter.setSlowSqlMillis(5000);
		filter.setLogSlowSql(true);
		filter.setMergeSql(true);
		return filter;
	}
	
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");
		Map initParameters = new HashMap<>();
		initParameters.put("loginUsername", "admin");
		initParameters.put("loginPassword", "admin");
		servletRegistrationBean.setInitParameters(initParameters);

		return servletRegistrationBean;
	}
}
