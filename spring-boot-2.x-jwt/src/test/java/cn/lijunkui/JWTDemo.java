package cn.lijunkui;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JWTDemo {
	private Logger log = LoggerFactory.getLogger(JWTDemo.class);
	@Test
	public void createToken() {

		String secret = "secret";// token 密钥
		Algorithm algorithm = Algorithm.HMAC256("secret");

		// 头部信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");

		Date nowDate = new Date();
		Date expireDate = getAfterDate(nowDate, 0, 0, 0, 2, 0, 0);// 2小过期
		
		String token = JWT.create()
			.withHeader(map)// 设置头部信息 Header 
			.withIssuer("SERVICE")//设置 载荷 签名是有谁生成 例如 服务器
			.withSubject("this is test token")//设置 载荷 签名的主题
			// .withNotBefore(new Date())//设置 载荷 定义在什么时间之前，该jwt都是不可用的.
			.withAudience("APP")//设置 载荷 签名的观众 也可以理解谁接受签名的
			.withIssuedAt(nowDate) //设置 载荷 生成签名的时间
			.withExpiresAt(expireDate)//设置 载荷 签名过期的时间
			.sign(algorithm);//签名 Signature
		Assert.assertTrue(token.length() > 0);
	}
	
	@Test
	public void createTokenWithClaim() {
		
		    Map<String, Object> map = new HashMap<String, Object>();
	        map.put("alg", "HS256");
	        map.put("typ", "JWT");
	        
	        Date nowDate = new Date();
			Date expireDate = getAfterDate(nowDate,0,0,0,2,0,0);//2小过期
	       
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    String token = JWT.create()
		    	.withHeader(map)
		    	/*设置 载荷 Payload*/
		    	.withClaim("loginName", "zhuoqianmingyue")
		        .withIssuer("SERVICE")//签名是有谁生成 例如 服务器
		        .withSubject("this is test token")//签名的主题
		        //.withNotBefore(new Date())//该jwt都是不可用的时间
		        .withAudience("APP")//签名的观众 也可以理解谁接受签名的
		        .withIssuedAt(nowDate) //生成签名的时间
		        .withExpiresAt(expireDate)//签名过期的时间
		        /*签名 Signature */
		        .sign(algorithm);
		    
		    Assert.assertTrue(token.length() > 0);
	}
	
	@Test
	public void createTokenWithChineseClaim() {

		Date nowDate = new Date();
		Date expireDate = getAfterDate(nowDate, 0, 0, 0, 2, 0, 0);// 2小过期

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");

		Algorithm algorithm = Algorithm.HMAC256("secret");
		String token = JWT.create().withHeader(map)
				/* 设置 载荷 Payload */
				.withClaim("loginName", "zhuoqianmingyue").withClaim("userName", "张三").withClaim("deptName", "技术部")
				.withIssuer("SERVICE")// 签名是有谁生成 例如 服务器
				.withSubject("this is test token")// 签名的主题
				// .withNotBefore(new Date())//该jwt都是不可用的时间
				.withAudience("APP")// 签名的观众 也可以理解谁接受签名的
				.withIssuedAt(nowDate) // 生成签名的时间
				.withExpiresAt(expireDate)// 签名过期的时间
				/* 签名 Signature */
				.sign(algorithm);
		
		Assert.assertTrue(token.length() > 0);
		
	}
	
	@Test
	public void verifyToken() throws UnsupportedEncodingException {
		String token = createTokenWithChineseClaim2();
		
		Algorithm algorithm = Algorithm.HMAC256("secret");
		JWTVerifier verifier = JWT.require(algorithm).withIssuer("SERVICE").build(); // Reusable verifier instance
		DecodedJWT jwt = verifier.verify(token);
		
		String subject = jwt.getSubject();
		List<String> audience = jwt.getAudience();
		Map<String, Claim> claims = jwt.getClaims();
		for (Entry<String, Claim> entry : claims.entrySet()) {
			String key = entry.getKey();
			Claim claim = entry.getValue();
			log.info("key:" + key + " value:" + claim.asString());
		}
		Claim claim = claims.get("loginName");

		log.info(claim.asString());
		log.info(subject);
		log.info(audience.get(0));

	}
	public String createTokenWithChineseClaim2() throws UnsupportedEncodingException {

		Date nowDate = new Date();
		Date expireDate = getAfterDate(nowDate, 0, 0, 0, 2, 0, 0);// 2小过期

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");

		User user = new User();
		user.setUserNaem("张三");
		user.setDeptName("技术部");
		Gson gson = new Gson();
		String userJson = gson.toJson(user);
		// String encode = URLEncoder.encode(userJson, "UTF-8");

		String userJsonBase64 = BaseEncoding.base64().encode(userJson.getBytes());

		Algorithm algorithm = Algorithm.HMAC256("secret");
		String token = JWT.create().withHeader(map)

				.withClaim("loginName", "zhuoqianmingyue").withClaim("user", userJsonBase64).withIssuer("SERVICE")// 签名是有谁生成
				.withSubject("this is test token")// 签名的主题
				// .withNotBefore(new Date())//该jwt都是不可用的时间
				.withAudience("APP")// 签名的观众 也可以理解谁接受签名的
				.withIssuedAt(nowDate) // 生成签名的时间
				.withExpiresAt(expireDate)// 签名过期的时间
				/* 签名 Signature */
				.sign(algorithm);

		return token;
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
}
