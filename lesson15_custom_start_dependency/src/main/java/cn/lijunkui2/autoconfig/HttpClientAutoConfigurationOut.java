package cn.lijunkui2.autoconfig;

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

@Configuration
@ConditionalOnClass({HttpClient.class})
@EnableConfigurationProperties(HttpClientPropertiesOut.class)
public class HttpClientAutoConfigurationOut {
	private  HttpClientPropertiesOut httpClientPropertiesOut;
	
	public HttpClientAutoConfigurationOut(HttpClientPropertiesOut httpClientPropertiesOut) {
		this.httpClientPropertiesOut = httpClientPropertiesOut;
	}
	@Bean
	@ConditionalOnMissingBean(HttpClient.class)
	public HttpClient httpClientOut() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(httpClientPropertiesOut.getConnectTimeout())
				.setSocketTimeout(httpClientPropertiesOut.getSocketTimeout()).build();
		
		HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
				.setUserAgent(httpClientPropertiesOut.getAgent()).setMaxConnPerRoute(httpClientPropertiesOut.getMaxPerRoute())
				.setConnectionReuseStrategy(new NoConnectionReuseStrategy()).build();
		return httpClient;
	}
}
