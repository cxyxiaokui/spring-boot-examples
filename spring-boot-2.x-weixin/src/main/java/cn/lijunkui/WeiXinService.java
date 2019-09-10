package cn.lijunkui;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.lijunkui.config.WeiXinConfig;

@Component
public class WeiXinService {
	
	private static final String AUTHORIZEURL="https://open.weixin.qq.com/connect/oauth2/authorize";
	private static final String GE_TACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	private static final String GE_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
	
	@Autowired
	private  WeiXinConfig weiXinConfig;
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * 拼接用户授权重定向的URL
	 * @param url
	 * @return
	 */
	public String buildAuthorizeURL(String url){
		
		return concatAuthorizeURL(url);
	}
	/**
	 * 获取微信用户的Opid
	 * @param code
	 * @return
	 */
	public Map<String,Object> getOpenIdInfo(String code){
		
		String getAccessTokenUrl = concatGetOpenIdInfoURL(code);
		String json = postRequestForWechat(getAccessTokenUrl);
		Map<String,Object> map = jsonToMap(json);
		
		return map;
	}
	
	/**
	 * 获取用户信息
	 * @param map
	 * @return
	 */
	public Map getWeiXinUserInfo(Map<String, Object> map) {
		
		String getUserInfoUrl = concatGetWeiXinUserInfoURL(map);
		String json = getRequestForWechat(getUserInfoUrl);
		Map userInfoMap = jsonToMap(json);
		
		return userInfoMap;
	}
	
	private String concatAuthorizeURL(String url) {
		StringBuilder authorizeUrl = new StringBuilder(AUTHORIZEURL);
		authorizeUrl.append("?appid=").append(weiXinConfig.getAppID());
		authorizeUrl.append("&redirect_uri=").append(url);
		authorizeUrl.append("&response_type=code");
		 //snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
		 //snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）
		authorizeUrl.append("&scope=snsapi_userinfo");
		authorizeUrl.append("&state=").append("STATE");
		authorizeUrl.append("#wechat_redirect");
		return authorizeUrl.toString();
	}

	
	
	private Map jsonToMap(String json) {
		Gson gons = new Gson();
		Map map = gons.fromJson(json, new TypeToken<Map>(){}.getType());
		return map;
	}

	private String postRequestForWechat(String getAccessTokenUrl) {
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(getAccessTokenUrl, null, String.class);
		String json = postForEntity.getBody();
		return json;
	}

	private String concatGetOpenIdInfoURL(String code) {
		StringBuilder getAccessTokenUrl = new StringBuilder(GE_TACCESSTOKEN_URL);
		getAccessTokenUrl.append("?appid=").append(weiXinConfig.getAppID());
		getAccessTokenUrl.append("&secret=").append(weiXinConfig.getAppsecret());
		getAccessTokenUrl.append("&code=").append(code);
		getAccessTokenUrl.append("&grant_type=authorization_code");
		return getAccessTokenUrl.toString();
	}

	private String getRequestForWechat(String getUserInfoUrl) {
		ResponseEntity<String> postForEntity = restTemplate.getForEntity(getUserInfoUrl.toString(), String.class);
		String json = postForEntity.getBody();
		return json;
	}

	private String concatGetWeiXinUserInfoURL(Map<String, Object> map) {
		String openId = (String) map.get("openid");
		String access_token = (String) map.get("access_token");
		// 检验授权凭证（access_token）是否有效
		StringBuilder getUserInfoUrl = new StringBuilder(GE_USERINFO_URL);
		getUserInfoUrl.append("?access_token=").append(access_token);
		getUserInfoUrl.append("&openId=").append(openId);
		getUserInfoUrl.append("&lang=zh_CN");
		
		return getUserInfoUrl.toString();
	}
}
