package cn.lijunkui2.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="spring.httpclient")
public class HttpClientPropertiesOut {
	private Integer connectTimeout = 1000;//创建连接的最长时间
	private Integer socketTimeout = 10000;//数据传输的最长时间
	private String agent = "agent";
	private Integer maxPerRoute = 10;//设置每个路由的并发数
	private Integer maxTotal = 50;//最大连接数 
	public Integer getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public Integer getSocketTimeout() {
		return socketTimeout;
	}
	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public Integer getMaxPerRoute() {
		return maxPerRoute;
	}
	public void setMaxPerRoute(Integer maxPerRoute) {
		this.maxPerRoute = maxPerRoute;
	}
	public Integer getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}
	
	
}
