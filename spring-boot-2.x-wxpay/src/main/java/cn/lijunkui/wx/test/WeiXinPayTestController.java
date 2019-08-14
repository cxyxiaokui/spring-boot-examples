package cn.lijunkui.wx.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.XmlUtil;
import cn.lijunkui.wx.config.WeiXinPayProperties;
import cn.lijunkui.wx.utils.WeiXinUtil;

@RestController
@RequestMapping("/wx")
public class WeiXinPayTestController {
	@Autowired
	private WeiXinPayProperties weiXinPayProperties;
	
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * 正式支付
	 * @return
	 */
	@RequestMapping("/pay")
	public Map<String, Object> realpay(){
		//配置微信支付基础信息参数
		Map<String, String> requestData = new HashMap<String, String>();
		requestData.put("appid", weiXinPayProperties.getAppid());//公众账号ID
		requestData.put("mch_id", weiXinPayProperties.getMchId());//商户号
		requestData.put("nonce_str", RandomUtil.randomString(15));//随机字符串 32位以内
		requestData.put("spbill_create_ip", "127.0.0.1");//终端IP 支持IPV4和IPV6两种格式的IP地址 123.12.12.123
		requestData.put("trade_type", "NATIVE");//交易类型 扫码支付
		//配置微信支付自定义支付信息参数
		requestData.put("attach", "附加数据远洋返回");
		requestData.put("body", "订单号 BW_000001");//商品简单描述
		requestData.put("out_trade_no", "BW_000001");//商户订单号
		requestData.put("total_fee", WeiXinUtil.getMoney("0.01"));//标价金额 按照分进行计算
		requestData.put("notify_url", "www.beiwaiclass.com");//通知地址 异步接收微信支付结果通知的回调地址必须外网访问 不能携带参数
		//配置微信支付sign信息参数
		String sign = WeiXinUtil.generateSign(requestData,weiXinPayProperties.getKey());//生成签名
		requestData.put("sign", sign);
		//将map信息转换成String
		String mapToXmlStr = XmlUtil.mapToXmlStr(requestData, "xml");
		
		//调用微信统一下单Api 将xml的String信息作为参数
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> formEntity = new HttpEntity<>(mapToXmlStr, headers);
		ResponseEntity<String> postForEntity = restTemplate.postForEntity("https://api.mch.weixin.qq.com/pay/unifiedorder", formEntity, String.class);
		
		//获取微信返回的信息
		String returnXmlString = postForEntity.getBody();
		Map<String, Object> xmlToMap = XmlUtil.xmlToMap(returnXmlString);
		
		return xmlToMap;
	}
	
	@RequestMapping("/query")
	public Map<String, Object> query(){
		
		//配置微信支付基础信息参数
		Map<String, String> requestData = new HashMap<String, String>();
		requestData.put("appid", weiXinPayProperties.getAppid());//公众账号ID
		requestData.put("mch_id", weiXinPayProperties.getMchId());//商户号
		requestData.put("nonce_str", RandomUtil.randomString(15));//随机字符串 32位以内
		requestData.put("spbill_create_ip", "127.0.0.1");//终端IP 支持IPV4和IPV6两种格式的IP地址 123.12.12.123
		//配置微信支付查询订单号参数
		requestData.put("out_trade_no", "bc1545094463077");//商户订单号
		//配置微信支付查询sign信息参数
		String sign = WeiXinUtil.generateSign(requestData,weiXinPayProperties.getKey());//生成签名
		requestData.put("sign", sign);
		//将map信息转换成String
		String mapToXmlStr = XmlUtil.mapToXmlStr(requestData, "xml");
		
		//调用微信统一下单Api 将xml的String信息作为参数
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> formEntity = new HttpEntity<>(mapToXmlStr, headers);
		ResponseEntity<String> postForEntity = restTemplate.postForEntity("https://api.mch.weixin.qq.com/pay/orderquery", formEntity, String.class);
		
		//获取微信返回的信息
		String returnXmlString = postForEntity.getBody();
		//{transaction_id=4200000222201812188586677386, nonce_str=h0brpFaoGxBzUUwF, trade_state=SUCCESS, bank_type=CFT, openid=o1xhEt4JzHFnJsm8j6oC9LDhRRWM, sign=69D08C2423F9E5ED3B8112AF50B4D50B, return_msg=OK, fee_type=CNY, mch_id=1289491501, cash_fee=10, out_trade_no=bc1545094463077, appid=wx071d3a5419eee575, total_fee=10, trade_state_desc=支付成功, trade_type=NATIVE, result_code=SUCCESS, attach=, time_end=20181218085439, is_subscribe=Y, return_code=SUCCESS}
		Map<String, Object> xmlToMap = XmlUtil.xmlToMap(returnXmlString);
		
		return xmlToMap;
	}
	/**
	 * 沙箱支付
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sandboxPay")
	public Map<String, Object> sandboxPay() throws Exception{
		//微信支付基础信息参数
		Map<String, String> requestData = new HashMap<String, String>();
		requestData.put("appid", weiXinPayProperties.getAppid());//公众账号ID
		requestData.put("mch_id", weiXinPayProperties.getMchId());//商户号
		requestData.put("nonce_str", RandomUtil.randomString(15));//随机字符串 32位以内
		requestData.put("spbill_create_ip", "127.0.0.1");//终端IP 支持IPV4和IPV6两种格式的IP地址 123.12.12.123
		requestData.put("trade_type", "NATIVE");//交易类型 扫码支付
		//微信支付自定义信息参数
		requestData.put("attach", "附加数据远洋返回");
		requestData.put("body", "订单号 BW_000001");//商品简单描述
		requestData.put("out_trade_no", "BW_000001");//商户订单号
		requestData.put("total_fee", WeiXinUtil.getMoney("101"));//标价金额 按照分进行计算
		requestData.put("notify_url", "www.beiwaiclass.com");//通知地址 异步接收微信支付结果通知的回调地址必须外网访问 不能携带参数
		//微信支付密钥信息参数
		String sign = WeiXinUtil.generateSign(requestData,weiXinPayProperties.getSandboxKey());//生成签名
		requestData.put("sign", sign);
		//将map信息转换成String
		String mapToXmlStr = XmlUtil.mapToXmlStr(requestData, "xml");
		
		//调用微信统一下单Api 将xml的String信息作为参数
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> formEntity = new HttpEntity<>(mapToXmlStr, headers);
		ResponseEntity<String> postForEntity = restTemplate
				.postForEntity("https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder", formEntity, String.class);
		
		//获取微信返回的信息
		String returnXmlString = postForEntity.getBody();
		Map<String, Object> xmlToMap = XmlUtil.xmlToMap(returnXmlString);
		
		return xmlToMap;
	}
	
	/**
     * 获取沙箱环境验签秘钥API
     * <a href="https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=23_1">获取验签秘钥API文档</a>
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
        
  		ResponseEntity<String> postForEntity = restTemplate.postForEntity("https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey", formEntity, String.class);
  		String returnXmlString = postForEntity.getBody();

  		Map<String, Object> responseMap = XmlUtil.xmlToMap(returnXmlString);

        return responseMap;
    }
	
}
