package cn.lijunkui;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.lijunkui.config.WeiXinConfig;

@Component
public class WeiXinService {
	
	private static final String GETWEIXINUSERINFO_AUTHORIZE="https://open.weixin.qq.com/connect/oauth2/authorize";
	private static final String GETWEIXINUSERINFO_GETACCESSTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
	private static final String GETWEIXINUSERINFO_GETUSERINFO = "https://api.weixin.qq.com/sns/userinfo";
	private static final String JSSDK_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String JSSDK_GETTICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	@Autowired
	private  WeiXinConfig weiXinConfig;
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 构建引导用户授权重定向的URL
	 * @param url
	 * @return
	 */
	public String buildAuthorizeURL(String url){
		return concatAuthorizeURL(url);
	}
	
	/**
	 * 拼接引导用户授权重定向的URL
	 * snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid）
	 * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）
	 * @param url
	 * @return
	 */
	private String concatAuthorizeURL(String url) {
		StringBuilder authorizeUrl = new StringBuilder(GETWEIXINUSERINFO_AUTHORIZE);
		authorizeUrl.append("?appid=").append(weiXinConfig.getAppID());
		authorizeUrl.append("&redirect_uri=").append(url);
		authorizeUrl.append("&response_type=code");
		authorizeUrl.append("&scope=snsapi_userinfo");
		authorizeUrl.append("&state=").append("STATE");
		authorizeUrl.append("#wechat_redirect");
		return authorizeUrl.toString();
	}
	
	/**
	 * 获取微信用户的 accesstoken 和 openid
	 * @param code
	 * @return
	 */
	public Map<String,Object> getACCESSTOKEN(String code){
		
		String getAccessTokenUrl = concatGetAccessTokenInfoURL(code);
		String json = postRequestForWeiXinService(getAccessTokenUrl);
		Map<String,Object> map = jsonToMap(json);
		
		return map;
	}
	/**
	 * 拼接调用微信服务获取  accesstoken 和 openid URL
	 * @param code
	 * @return
	 */
	private String concatGetAccessTokenInfoURL(String code) {
		StringBuilder getAccessTokenUrl = new StringBuilder(GETWEIXINUSERINFO_GETACCESSTOKEN);
		getAccessTokenUrl.append("?appid=").append(weiXinConfig.getAppID());
		getAccessTokenUrl.append("&secret=").append(weiXinConfig.getAppsecret());
		getAccessTokenUrl.append("&code=").append(code);
		getAccessTokenUrl.append("&grant_type=authorization_code");
		return getAccessTokenUrl.toString();
	}

	/**
	 * 获取微信用户信息
	 * @param map
	 * @return
	 */
	public Map getWeiXinUserInfo(Map<String, Object> map) {
		
		String getUserInfoUrl = concatGetWeiXinUserInfoURL(map);
		String json = getRequestForWeiXinService(getUserInfoUrl);
		Map userInfoMap = jsonToMap(json);
		
		return userInfoMap;
	}
	/**
	 * 拼接调用微信服务获取用户信息的URL
	 * @param map
	 * @return
	 */
	private String concatGetWeiXinUserInfoURL(Map<String, Object> map) {
		String openId = (String) map.get("openid");
		String access_token = (String) map.get("access_token");
		// 检验授权凭证（access_token）是否有效
		StringBuilder getUserInfoUrl = new StringBuilder(GETWEIXINUSERINFO_GETUSERINFO);
		getUserInfoUrl.append("?access_token=").append(access_token);
		getUserInfoUrl.append("&openId=").append(openId);
		getUserInfoUrl.append("&lang=zh_CN");
		
		return getUserInfoUrl.toString();
	}
	/**
	 * 初始化JSSDK配置信息
	 * @param shareUrl
	 * @return
	 * @throws Exception
	 */
	public Map initJSSDKConfigInfo(String shareUrl) throws Exception {
		
		String accessToken = this.getJSSDKAccessToken();
		String jsapiTicket = this.getJSSDKJsapiTicket(accessToken);
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String nonceStr = UUID.randomUUID().toString();
		String signature = this.buildJSSDKSignature(jsapiTicket,timestamp,nonceStr,shareUrl);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("shareUrl", shareUrl);
		map.put("jsapi_ticket", jsapiTicket);
		map.put("nonceStr", nonceStr);
		map.put("timestamp", timestamp);
		map.put("signature", signature);
		map.put("appid", weiXinConfig.getAppID());
		return map;
	}
	public String getJSSDKAccessToken() {
		String token = null;
		String url = JSSDK_ACCESSTOKEN.replaceAll("APPID",
				weiXinConfig.getAppID()).replaceAll("APPSECRET",
				weiXinConfig.getAppsecret());

		String json = postRequestForWeiXinService(url);
		Map map = jsonToMap(json);
		if (map != null) {
			token = (String) map.get("access_token");
		}
		return token;
	}
	
	public String getJSSDKJsapiTicket(String token) {
	  String url = JSSDK_GETTICKET.replaceAll("ACCESS_TOKEN", token);
	  String json = postRequestForWeiXinService(url);
	  Map map = jsonToMap(json);
	  String jsapi_ticket = null;
	  if (map != null) {
          jsapi_ticket = (String) map.get("ticket");
      }
	  return jsapi_ticket;
	}
	
	 /**
     * 构建分享链接的签名。
     * @param ticket
     * @param nonceStr
     * @param timeStamp
     * @param url
     * @return
     * @throws Exception
     */
    public static String buildJSSDKSignature(String ticket,String timestamp,String nonceStr ,String url) throws Exception {
    	
    	String orderedString = "jsapi_ticket=" + ticket
                + "&noncestr=" + nonceStr + "&timestamp=" + timestamp
                + "&url=" + url;
        return sha1(orderedString);
    }
    
    /**
     * sha1 加密JSSDK微信配置参数获取签名。
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static String sha1(String orderedString) throws Exception {
        String ciphertext = null;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(orderedString.getBytes());
        ciphertext = byteToStr(digest);
        return ciphertext.toLowerCase();
    }
    /** 
     * 将字节数组转换为十六进制字符串 
     *  
     * @param byteArray 
     * @return 
     */ 
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
    /** 
     * 将字节转换为十六进制字符串 
     *  
     * @param mByte 
     * @return 
     */ 
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  

        String s = new String(tempArr);  
        return s;  
    }
    
	public String mapToJson(Map map){
		Gson gson = new Gson();
		String json = gson.toJson(map);
		return json;
	}
	private Map jsonToMap(String json) {
		Gson gons = new Gson();
		Map map = gons.fromJson(json, new TypeToken<Map>(){}.getType());
		return map;
	}

	private String postRequestForWeiXinService(String getAccessTokenUrl) {
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(getAccessTokenUrl, null, String.class);
		String json = postForEntity.getBody();
		return json;
	}
	
	private String getRequestForWeiXinService(String getUserInfoUrl) {
		ResponseEntity<String> postForEntity = restTemplate.getForEntity(getUserInfoUrl.toString(), String.class);
		String json = postForEntity.getBody();
		return json;
	}
}
