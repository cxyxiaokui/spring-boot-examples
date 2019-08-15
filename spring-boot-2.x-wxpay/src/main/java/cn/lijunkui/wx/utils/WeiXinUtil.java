package cn.lijunkui.wx.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cn.hutool.crypto.SecureUtil;

public class WeiXinUtil {

	
	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	public static String generateSign(Map<String, String> requestData,String key) {
		
		TreeMap<String, String> sortMapByKey = (TreeMap<String, String>) sortMapByKey(requestData);
		StringBuffer keyWithValue = splicingKeyAndValue(sortMapByKey);
		keyWithValue.append("key=" + key);
		
		String sign = SecureUtil.md5().digestHex(keyWithValue.toString(), "UTF-8");
		return sign;

	}
	/**
	 * 拼接Map中的key和value通过&
	 * @param sortMapByKey
	 * @return
	 */
	private static StringBuffer splicingKeyAndValue(TreeMap<String, String> sortMapByKey) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = sortMapByKey.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k) && ! "sign_type".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		return sb;
	}
	/**
     * Map 按key排序
     * @param oriMap
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> oriMap) {  
        if (oriMap == null || oriMap.isEmpty()) {  
            return null;  
        }  
        Map<String, String> sortedMap = new TreeMap<String, String>();  
        sortedMap.putAll(oriMap);  
        return sortedMap;  
    }
	/**
	 * 元转换成分
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if(amount==null){
			return "";
		}
		// 金额转化为分为单位
		String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
		int index = currency.indexOf(".");  
		int length = currency.length();  
		Long amLong = 0l;  
		if(index == -1){  
			amLong = Long.valueOf(currency+"00");  
		}else if(length - index >= 3){  
			amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
		}else if(length - index == 2){  
			amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
		}else{  
			amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
		}  
		return amLong.toString(); 
	}
	
	 /**
     * XML格式字符串转换为Map
     *
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
        InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
        org.w3c.dom.Document doc = documentBuilder.parse(stream);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int idx=0; idx<nodeList.getLength(); ++idx) {
            Node node = nodeList.item(idx);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                data.put(element.getNodeName(), element.getTextContent());
            }
        }
        try {
            stream.close();
        }
        catch (Exception ex) {

        }
        return data;
    }
}
