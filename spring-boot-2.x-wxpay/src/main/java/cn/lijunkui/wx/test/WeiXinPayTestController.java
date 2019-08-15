package cn.lijunkui.wx.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.github.wxpay.sdk.WXPayUtil;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.XmlUtil;
import cn.lijunkui.wx.config.WeiXinPayProperties;
import cn.lijunkui.wx.utils.WeiXinUtil;

@Controller
@RestController
@RequestMapping("/wx")
public class WeiXinPayTestController {
	
	private static final String UNIFIEDORDERURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	private static final String GETSIGNKEYURL = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
	private static final String SANDBOXUNIFIEDORDERURL = "https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";
	private static final String ORDERQUERYURL = "https://api.mch.weixin.qq.com/pay/orderquery";
	
	@Autowired
	private WeiXinPayProperties weiXinPayProperties;
	
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * 正式支付
	 * @return
	 */
	@RequestMapping("/pay")
	public String realpay(){
		
		/*配置微信支付基础信息参数*/
		Map<String, String> requestData = new HashMap<String, String>();
		requestData.put("appid", weiXinPayProperties.getAppid());//公众账号ID
		requestData.put("mch_id", weiXinPayProperties.getMchId());//商户号
		requestData.put("nonce_str", RandomUtil.randomString(15));//随机字符串 32位以内
		requestData.put("spbill_create_ip", "127.0.0.1");//终端IP 支持IPV4和IPV6
		requestData.put("trade_type", "NATIVE");//交易类型 扫码支付
		/*配置微信支付自定义支付信息参数*/
		requestData.put("attach", "附加数据远洋返回");
		requestData.put("body", "订单号 BW_000001");//商品简单描述
		requestData.put("out_trade_no", "BW_000001");//商户订单号
		requestData.put("total_fee", WeiXinUtil.getMoney("0.01"));//标价金额 按照分进行计算
		requestData.put("notify_url", "www.beiwaiclass.com");//通知地址 异步接收微信支付结果通知的回调地址必须外网访问 不能携带参数
		
		/*配置微信支付sign信息参数*/
		String sign = null;
		String payUrl = null;
		if(Boolean.valueOf(weiXinPayProperties.getUseSandbox())){
			 sign = WeiXinUtil.generateSign(requestData,weiXinPayProperties.getKey());//生成签名
			 payUrl = UNIFIEDORDERURL;
		}else{
			 sign = WeiXinUtil.generateSign(requestData,weiXinPayProperties.getSandboxKey());//生成签名
			 payUrl = SANDBOXUNIFIEDORDERURL;
		}
		
		requestData.put("sign", sign);
		
		/*将map信息转换成String*/
		String mapToXmlStr = XmlUtil.mapToXmlStr(requestData, "xml");
		
		/*调用微信统一下单Api将mapToXmlStr作为参数*/
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> formEntity = new HttpEntity<>(mapToXmlStr, headers);
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(payUrl, formEntity, String.class);
		
		//获取微信返回的信息
		String returnXmlString = postForEntity.getBody();
		Map<String, Object> xmlToMap = XmlUtil.xmlToMap(returnXmlString);
		String returnCode = (String)xmlToMap.get("return_code");
		if("SUCCESS".equals(returnCode)){
			String codeUrl = (String)xmlToMap.get("code_url");
			return codeUrl;
		}
		
		return "";
	}
	
	/**
	 * 支付回调
	 * @throws Exception 
	 */
	@RequestMapping("/wxNotify")
	public void wxNotify(HttpServletRequest request) throws Exception{
		Map<String, String> parseNotifyParameter = parseNotifyParameter(request);
		String sign = WeiXinUtil.generateSign(parseNotifyParameter,weiXinPayProperties.getSandboxKey());//生成签名
		if(sign.equals(parseNotifyParameter.get("sign"))){
			//支付成功
		}
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/query")
	public String query(String orderSn){
		
		if(StringUtils.isBlank(orderSn)){
			throw new RuntimeException("订单号不能为空！");
		}
		
		//配置微信支付基础信息参数
		Map<String, String> requestData = new HashMap<String, String>();
		requestData.put("appid", weiXinPayProperties.getAppid());//公众账号ID
		requestData.put("mch_id", weiXinPayProperties.getMchId());//商户号
		requestData.put("nonce_str", RandomUtil.randomString(15));//随机字符串 32位以内
		requestData.put("spbill_create_ip", "127.0.0.1");//终端IP 支持IPV4和IPV6两种格式的IP地址 123.12.12.123
		
		//配置微信支付查询订单号参数
		requestData.put("out_trade_no", orderSn);//商户订单号
		
		//配置微信支付查询sign信息参数
		String sign = WeiXinUtil.generateSign(requestData,weiXinPayProperties.getKey());//生成签名
		requestData.put("sign", sign);
		
		//将map信息转换成String
		String mapToXmlStr = XmlUtil.mapToXmlStr(requestData, "xml");
		//调用微信统一下单Api 将xml的String信息作为参数
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> formEntity = new HttpEntity<>(mapToXmlStr, headers);
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(ORDERQUERYURL, formEntity, String.class);
		
		//获取微信返回的信息
		String returnXmlString = postForEntity.getBody();
		
		Map<String, Object> xmlToMap = XmlUtil.xmlToMap(returnXmlString);
		String returnCode = (String)xmlToMap.get("return_code");
		if("SUCCESS".equals(returnCode)){
			return "支付成功！";
		}else{
			return "";
		}
	}
	
	/**
     * 获取沙箱环境验签秘钥API
     * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=23_1
     * @return
     * @throws Exception
     */
	@RequestMapping("/getSignKey")
    public Map<String, Object> getSignKey() throws Exception {
		
        Map<String, String> requestData = new HashMap<>();
        requestData.put("appid", weiXinPayProperties.getAppid());
        requestData.put("mch_id", weiXinPayProperties.getMchId());
        requestData.put("nonce_str", RandomUtil.randomString(15));
        String sign = WeiXinUtil.generateSign(requestData,weiXinPayProperties.getKey());//生成签名
        requestData.put("sign", sign);
        
        String mapToXmlStr = XmlUtil.mapToXmlStr(requestData, "xml");
        //调用微信统一下单Api 将xml的String信息作为参数
  		HttpHeaders headers = new HttpHeaders();
  		headers.setContentType(MediaType.APPLICATION_XML);
  		HttpEntity<String> formEntity = new HttpEntity<>(mapToXmlStr, headers);
        
  		ResponseEntity<String> postForEntity = restTemplate.postForEntity(GETSIGNKEYURL, formEntity, String.class);
  		String returnXmlString = postForEntity.getBody();

  		Map<String, Object> responseMap = XmlUtil.xmlToMap(returnXmlString);

        return responseMap;
    }
	
	
    /**
     * 从request的inputStream中获取参数
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String, String> parseNotifyParameter(HttpServletRequest request) throws Exception {
        InputStream inputStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, length);
        }
        outSteam.close();
        inputStream.close();

        // 获取微信调用我们notify_url的返回信息
        String resultXml = new String(outSteam.toByteArray(), "utf-8");
        Map<String, String> notifyMap = WeiXinUtil.xmlToMap(resultXml);
        return notifyMap;
    }
	
}
