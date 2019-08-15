package cn.lijunkui.wx.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=23_1
 * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=23_1&index=1
 * @author zhuoqianmingyue
 *
 */
@Component
@ConfigurationProperties(prefix="wx.pay")
public class WeiXinPayProperties {
	
	/**合作身份者ID */
	private String appid;
	/** 商户号 */
	private String mchId;
	/** 商户号密钥 */
	private String appsecret;
	/** API 密钥 商户后台配置的一个32位的key 微信商户平台-账户设置-安全设置-api安全 */
	private String key;
	/**是否使用沙箱*/
	private String useSandbox;
	/** 沙箱环境API 密钥  */
	private String sandboxKey;
	/** 回调地址 */
	private String notifyUrl;
	
	public String getUseSandbox() {
		return useSandbox;
	}
	public void setUseSandbox(String useSandbox) {
		this.useSandbox = useSandbox;
	}
	public String getSandboxKey() {
		return sandboxKey;
	}
	public void setSandboxKey(String sandboxKey) {
		this.sandboxKey = sandboxKey;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
