package cn.lijunkui.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.lijunkui.WeiXinService;


@RestController
@RequestMapping("/weixin")
public class WeiXinDemoController {
	@Autowired
	private WeiXinService weiXinService;

	@RequestMapping("/getWeiXinUserInfo")
	public String getWeiXinUserInfo(String code,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		//第一步：用户同意授权，获取code
		if (code == null) {
			String url = URLEncoder.encode(request.getRequestURL().toString());
			String authorizeUrl = weiXinService.buildAuthorizeURL(url);
			response.sendRedirect(authorizeUrl);
			return null;
		}
		//第二步：通过code换取网页授权access_token
		String htmlInfo = "";
		Map<String, Object> openIdInfo = weiXinService.getOpenIdInfo(code);
		String errcode = (String)openIdInfo.get("errcode");
		if(StringUtils.isEmpty(errcode)){
			//第四步：拉取用户信息(需scope为 snsapi_userinfo)
			Map<String, Object> weiXinUserInfo = weiXinService.getWeiXinUserInfo(openIdInfo);
			String userInfohtml = createUserInfoHtml(weiXinUserInfo);
			return userInfohtml;
		}
		return htmlInfo;
	}

	private String createUserInfoHtml(Map<String, Object> weiXinUserInfo) {
		String wxOpenId = (String)weiXinUserInfo.get("openid");
		String nickname = (String)weiXinUserInfo.get("nickname");
		Double sex = (Double)weiXinUserInfo.get("sex");
		String sexString = "";
		if(sex == 1.0d){
			sexString = "男";
		}else{
			sexString = "女";
		}
		/*String province = (String)weiXinUserInfo.get("province");
		String city = (String)weiXinUserInfo.get("city");
		String country = (String)weiXinUserInfo.get("country");*/
		String headimgurl = (String)weiXinUserInfo.get("headimgurl");

		String htmlInfo="<!DOCTYPE html>"+
		"<html lang='en'>"+
		  "<head>"+
		   	"  <meta charset='UTF-8'>"+
		   	"  <meta name='viewport' content='initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no'>"+
		  "</head>"+
		  "<body>"+
		  	"<h3>用户openId</h3>"+
		  	"<p>"+wxOpenId+"</p>"+
		  	"<h3>普通用户昵称</h3>"+
		  	"<p>"+nickname+"</p>"+
		  	"<h3>用户性别</h3>"+
		  	"<p>"+sexString+"</p>"+
		  	"<h3>用户头像</h3>"+
		  	"<img src=\""+headimgurl+"\"></img>"+
		  "</body>"+
		"</html>";
		return htmlInfo;
	}
}
