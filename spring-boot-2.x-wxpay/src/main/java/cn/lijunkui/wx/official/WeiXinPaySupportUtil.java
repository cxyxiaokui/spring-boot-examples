package cn.lijunkui.wx.official;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.wxpay.sdk.WXPayUtil;

public class WeiXinPaySupportUtil {
	
	
    /**
     * 从request的inputStream中获取参数
     * code come from https://github.com/mengday/springboot-pay-example/blob/master/src/main/java/com/example/pay/configuration/WXPayClient.java
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> getNotifyParameter(HttpServletRequest request) throws Exception {
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
        Map<String, String> notifyMap = WXPayUtil.xmlToMap(resultXml);

        return notifyMap;
    }
}
