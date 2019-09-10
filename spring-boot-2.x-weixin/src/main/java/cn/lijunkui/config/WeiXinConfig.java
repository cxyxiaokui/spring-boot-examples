package cn.lijunkui.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="wx")
public class WeiXinConfig {
	
	private String appID;
	private String mchID;
	private String appsecret;
	private String key;
	
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getMchID() {
		return mchID;
	}
	public void setMchID(String mchID) {
		this.mchID = mchID;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	
}
