package cn.lijunkui.jwt;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTDemo {
	
	public String createToken() {
		
		try {
			String secret = "secret";//token 密钥
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    //头部信息
		    Map<String, Object> map = new HashMap<String, Object>();
		    map.put("alg", "HS256");
	        map.put("typ", "JWT");
	        
		    Date nowDate = new Date();
		    Date expireDate = getAfterDate(nowDate,0,0,0,2,0,0);//2小过期
	    
		    String token = JWT.create()
		    	/*设置头部信息 Header*/
		    	.withHeader(map)
		    	/*设置 载荷 Payload*/
		        .withIssuer("SERVICE")//签名是有谁生成 例如 服务器
		        .withSubject("this is test token")//签名的主题
		        //.withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
		        .withAudience("APP")//签名的观众 也可以理解谁接受签名的
		        .withIssuedAt(nowDate) //生成签名的时间
		        .withExpiresAt(expireDate)//签名过期的时间
		        /*签名 Signature */
		        .sign(algorithm);
		    return token;
		} catch (JWTCreationException exception){
			exception.printStackTrace();
		}
		return null;
	}
	public String createTokenWithClaim() {
		
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    Map<String, Object> map = new HashMap<String, Object>();
		    Date nowDate = new Date();
		    Date expireDate = getAfterDate(nowDate,0,0,0,2,0,0);//2小过期
	        map.put("alg", "HS256");
	        map.put("typ", "JWT");
		    String token = JWT.create()
		    	/*设置头部信息 Header*/
		    	.withHeader(map)
		    	/*设置 载荷 Payload*/
		    	.withClaim("loginName", "lijunkui")
		        .withIssuer("SERVICE")//签名是有谁生成 例如 服务器
		        .withSubject("this is test token")//签名的主题
		        //.withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
		        .withAudience("APP")//签名的观众 也可以理解谁接受签名的
		        .withIssuedAt(nowDate) //生成签名的时间
		        .withExpiresAt(expireDate)//签名过期的时间
		        /*签名 Signature */
		        .sign(algorithm);
		    return token;
		} catch (JWTCreationException exception){
			exception.printStackTrace();
		}
		return null;
	}
	  /**
		 * 返回一定时间后的日期
		 * @param date 开始计时的时间
		 * @param year 增加的年
		 * @param month 增加的月
		 * @param day 增加的日
		 * @param hour 增加的小时
		 * @param minute 增加的分钟
		 * @param second 增加的秒
		 * @return
		 */
		public  Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second){
			if(date == null){
				date = new Date();
			}
			
			Calendar cal = new GregorianCalendar ();
			
			cal.setTime(date);
			if(year != 0){
				cal.add(Calendar.YEAR, year);
			}
			if(month != 0){
				cal.add(Calendar.MONTH, month);
			}
			if(day != 0){
				cal.add(Calendar.DATE, day);
			}
			if(hour != 0){
				cal.add(Calendar.HOUR_OF_DAY, hour);
			}
			if(minute != 0){
				cal.add(Calendar.MINUTE, minute);
			}
			if(second != 0){
				cal.add(Calendar.SECOND, second);
			}
			return cal.getTime();
		}
	public void verifyToken(String token) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("SERVICE")
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    String subject = jwt.getSubject();
		    Map<String, Claim> claims = jwt.getClaims();
		    Claim claim = claims.get("loginName");
		    System.out.println(claim.asString());
		    List<String> audience = jwt.getAudience();
		    System.out.println(subject);
		    System.out.println(audience.get(0));
		} catch (JWTVerificationException exception){
			exception.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JWTDemo demo = new JWTDemo();
		//String createToken = demo.createToken();
		String createTokenWithClaim = demo.createTokenWithClaim();
		demo.verifyToken(createTokenWithClaim);
	}
}
