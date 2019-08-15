package cn.lijunkui.wx.official.config;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
@Component
@ConfigurationProperties(prefix="wxpay")
public class WeiXinOfficPayProperties extends WXPayConfig {

	private String appID;
	private String mchID;
	private String key;
	private InputStream certStream;
	private int httpConnectTimeoutMs;
	private int httpReadTimeoutMs;
	private IWXPayDomain WXPayDomain;
	
	@Override
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

	public int getHttpConnectTimeoutMs() {
		return httpConnectTimeoutMs;
	}

	public void setHttpConnectTimeoutMs(int httpConnectTimeoutMs) {
		this.httpConnectTimeoutMs = httpConnectTimeoutMs;
	}

	public int getHttpReadTimeoutMs() {
		return httpReadTimeoutMs;
	}

	public void setHttpReadTimeoutMs(int httpReadTimeoutMs) {
		this.httpReadTimeoutMs = httpReadTimeoutMs;
	}

	
	public byte[] getCertData() {
		return certData;
	}

	public void setCertData(byte[] certData) {
		this.certData = certData;
	}

	public void setCertStream(InputStream certStream) {
		this.certStream = certStream;
	}

	private byte[] certData;

	public WeiXinOfficPayProperties() throws Exception {
		/*String certPath = "/path/to/apiclient_cert.p12";
		File file = new File(certPath);
		InputStream certStream = new FileInputStream(file);
		this.certData = new byte[(int) file.length()];
		certStream.read(this.certData);
		certStream.close();*/
	}

	public InputStream getCertStream() {
		ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
		return certBis;
	}

	public IWXPayDomain getWXPayDomain() {
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
        	@Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }

			@Override
			public void report(String domain, long elapsedTimeMillis,
					Exception ex) {
				
			}
        };
        return iwxPayDomain;

	}

	public void setWXPayDomain(IWXPayDomain WXPayDomain) {
		this.WXPayDomain = WXPayDomain;
	}
}
