package cn.lijunkui.robot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("tencent.ai")
public class TencentAIOpenPlatformProperties {
	private String appID;
	private String appKey;
	private String createChatUrl = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat";
	
	public String getCreateChatUrl() {
		return createChatUrl;
	}
	public void setCreateChatUrl(String createChatUrl) {
		this.createChatUrl = createChatUrl;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

}
