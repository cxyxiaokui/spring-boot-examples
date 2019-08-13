package cn.lijunkui.robot.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import cn.lijunkui.robot.TencentAIOpenPlatformProperties;
import cn.lijunkui.robot.util.TencentAIHelper;

@RestController
@RequestMapping("/rebot")
public class ReBotChatController {
	@Autowired
	private HttpClient httpClient;
	
	@Autowired
	private TencentAIOpenPlatformProperties tencentAIOpenPlatformProperties;
	
	@RequestMapping("/chat/{msg}")
	public String speak(@PathVariable("msg") String msg) throws ClientProtocolException, IOException, URISyntaxException{
		
		SortedMap<String, Object> params = new TreeMap<String,Object>();
        params.put("app_id", tencentAIOpenPlatformProperties.getAppID());
        params.put("time_stamp", new Date().getTime() / 1000);
        params.put("nonce_str", Math.random());
        params.put("question", msg);
        params.put("session", "10000");
        params.put("sign", TencentAIHelper.buildSign(params,tencentAIOpenPlatformProperties.getAppKey()));
        
        URI uri = new URI(tencentAIOpenPlatformProperties.getCreateChatUrl());
        URIBuilder uriBuilder = new URIBuilder(uri);
		 for (Map.Entry<String, Object> entry : params.entrySet()) {  
			 uriBuilder.addParameter(entry.getKey(),  
                     String.valueOf(entry.getValue()));  
         }  
		URI uriParma = uriBuilder.build();
		HttpGet httpGet = new HttpGet(uriParma);
		HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();
		String content = EntityUtils.toString(httpEntity, "utf-8");
		
		Map result = JSONObject.parseObject(content, Map.class);
		Integer ret = (Integer)result.get("ret");
		String answer = "哎哟 脑子瓦特了！";
		if(ret.intValue() == 0){
			JSONObject jSONObject = (JSONObject)result.get("data");
			answer = jSONObject.getString("answer");
		}
		return answer;
	} 
}
