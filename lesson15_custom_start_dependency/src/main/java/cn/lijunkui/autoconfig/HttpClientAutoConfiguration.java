package cn.lijunkui.autoconfig;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpClientErrorException;

/*@Configuration
@ConditionalOnClass({HttpClient.class})
@EnableConfigurationProperties(HttpClientProperties.class)*/
public class HttpClientAutoConfiguration {
	private  HttpClientProperties httpClientProperties;
	
	public HttpClientAutoConfiguration(HttpClientProperties httpClientProperties) {
		this.httpClientProperties = httpClientProperties;
	}
	@Bean
	@ConditionalOnMissingBean(HttpClient.class)
	public HttpClient httpClient() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(httpClientProperties.getConnectTimeout())
				.setSocketTimeout(httpClientProperties.getSocketTimeout()).build();
		
		HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
				.setUserAgent(httpClientProperties.getAgent()).setMaxConnPerRoute(httpClientProperties.getMaxPerRoute())
				.setConnectionReuseStrategy(new NoConnectionReuseStrategy()).build();
		return httpClient;
	}
}
