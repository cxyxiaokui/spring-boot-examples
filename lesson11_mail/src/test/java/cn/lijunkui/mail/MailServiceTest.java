package cn.lijunkui.mail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTest {
	
	@Autowired
	private MailService mailService;
	
	@Test
	public void sendSimpleMail() {
		mailService.sendSimpleMail("******@qq.com", "这是一个测试邮件", "这是一个测试邮件");
	}
	
	@Test
	public void sendHtmlMail() {
		String html= "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"<title>Insert title here</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<font color=\"red\">发送html</font>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		mailService.sendHtmlMail("******@qq.com", "这是一个测试邮件", html);
	}
	@Test
	public void sendInlineResourceMail() {
		String html= "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"<title>Insert title here</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<img src=\"cid:image1\"/> "+
				"<img src=\"cid:image2\"/> "+
				"	<font color=\"red\">发送html</font>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		List<InlineResource> list = new ArrayList<InlineResource>();
		String path = MailServiceTest.class.getClassLoader().getResource("image.jpg").getPath();
		
		InlineResource resource = new InlineResource("image1",path);
		InlineResource resource2 = new InlineResource("image2",path);
		
		list.add(resource2);
		list.add(resource);
		mailService.sendInlineResourceMail("******@qq.com", "头像图片", html,list);
		//mailService.sendInlineResourceMail("******@163.com", "这是一个测试邮件", html,list);
	}
	
}
