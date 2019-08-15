package cn.lijunkui.wx.official;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lijunkui.wx.official.config.WeiXinOfficPayProperties;

import com.github.wxpay.sdk.WXPay;

@RestController
@RequestMapping("/wxPayOfficial")
public class WxPayOfficialDemoController {
	
	@Autowired
	private WeiXinOfficPayProperties weiXinPayProperties;
	
	/**
	 * 微信扫码支付
	 * @return
	 */
	@RequestMapping("/naitvePay")
	public String naitvePay(){
		try {
			WXPay wxpay = new WXPay(weiXinPayProperties);
			 Map<String, String> data = new HashMap<String, String>();
		        data.put("body", "购买苹果10个");
		        data.put("out_trade_no", generateOrderSn());
		        data.put("device_info", "");
		        data.put("fee_type", "CNY");
		        data.put("total_fee", "1");
		        data.put("spbill_create_ip", "123.12.12.123");
		        data.put("notify_url", "http://localhost:8090/sbe2/wxPayOfficial/wxNotify");
		        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
		        data.put("product_id", "12");
		        Map<String, String> resp = wxpay.unifiedOrder(data);
		        String returnCode = resp.get("return_code");
				if("SUCCESS".equals(returnCode)){
					String codeUrl = resp.get("code_url");
					return codeUrl;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String generateOrderSn() {
		 //生成唯一id
        String id= UUID.randomUUID().toString().replace("-", "");
		return id;
	}

	/**
	 * 微信支付成功后回调地址
	 * @param request
	 */
	@RequestMapping("/wxNotify")
	public void wxNotify(HttpServletRequest request) {
		
		try {
			Map<String, String> notifyMap = WeiXinPaySupportUtil.getNotifyParameter(request);
			WXPay wxpay = new WXPay(weiXinPayProperties);
	        if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
	            // 签名正确
	            // 进行处理。
	            // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
	        }
	        else {
	            // 签名错误，如果数据里没有sign字段，也认为是签名错误
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 微信订单查询
	 * @param request
	 */
	@RequestMapping("/query")
	public void query(HttpServletRequest request) {
		try {
			WXPay wxpay = new WXPay(weiXinPayProperties);
			
			Map<String, String> data = new HashMap<String, String>();
		    data.put("out_trade_no", "2016090910595900000012");
			
			Map<String, String> resp = wxpay.orderQuery(data);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	
}
