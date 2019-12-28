package cn.zhuoqianmingyue;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cn.lijunkui.autoconfig.EnableHttpClient;

@Configuration
public class HttpClientConfig {
	//@Bean
	public HttpClient httpClient() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1500)
				.setSocketTimeout(15000).build();
		
		HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
				.setUserAgent("agent").setMaxConnPerRoute(10)
				.setConnectionReuseStrategy(new NoConnectionReuseStrategy()).build();
		return httpClient;
	}
}
