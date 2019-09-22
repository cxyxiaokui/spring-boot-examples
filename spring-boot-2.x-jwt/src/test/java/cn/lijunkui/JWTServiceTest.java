package cn.lijunkui;

import java.util.Date;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth0.jwt.algorithms.Algorithm;

import cn.lijunkui.jwt.Payload;
import cn.lijunkui.utils.JWTService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JWTServiceTest {
	
	@Autowired
	private JWTService jWTService;
	
	@Test
	public void verifyToken() {
		Payload payload = jWTService.createPayload("USERSERVICE","userLoginToken","APP",new Date(),1);
		String token = jWTService.createToken(payload, Algorithm.HMAC256("secret"));
		Payload verifyToken = jWTService.verifyToken(token);
		Assert.assertNotNull(verifyToken);
	}
}
