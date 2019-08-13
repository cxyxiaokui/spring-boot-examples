package cn.lijunkui.autoconfig;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HttpClientAutoConfigurationTest {
	@Autowired
	private HttpClient httpClient;
	@Test
	public void test() throws ClientProtocolException, IOException {
		HttpEntity httpEntity = httpClient.execute(new HttpGet("http://www.baidu.com")).getEntity();
		String content = EntityUtils.toString(httpEntity, "utf-8");
		System.out.println(content);
	}
}
