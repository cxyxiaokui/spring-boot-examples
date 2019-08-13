package cn.lijunkui.robot.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;

public class TencentAIHelper {
	
	public static String buildSign(SortedMap<String, Object> params, String privateKey)
			throws IOException {
		
		StringBuffer sb = new StringBuffer();
		Iterator<Entry<String, Object>> paramsIterator = params.entrySet().iterator();
		while (paramsIterator.hasNext()) {
			Entry<String, Object> entry = paramsIterator.next();
			String key = entry.getKey();
			String value = objectToString(entry.getValue());
			if (null != value && !"".equals(value) && !"sign".equals(key)
					&& !"key".equals(key)) {
				sb.append(key + "=" + URLEncoder.encode(value.toString(), "UTF-8") + "&");
			}
		}
		
		sb.append("app_key=" + privateKey);
		System.out.println(sb.toString());
		String sign = MD5Util.md5(sb.toString()).toUpperCase();
		return sign;
	}
	
	private static String objectToString(Object object) {
		
		if(object instanceof Double){
			return String.valueOf(object);
		}
		if(object instanceof Integer){
			return String.valueOf(object);
		}
		if(object instanceof Long){
			return String.valueOf(object);
		}
		if(object instanceof Float){
			return String.valueOf(object);
		}
		if(object instanceof Boolean){
			return String.valueOf(object);
		}
		
		return object.toString();
	}

}
