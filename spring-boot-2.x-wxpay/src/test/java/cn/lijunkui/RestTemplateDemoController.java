package cn.lijunkui;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import cn.lijunkui.resttemplate.model.User;
/**
 * 默认使用 SimpleClientHttpRequestFactory 使用标准JDK工具的实现。
 * 
 * @author zhuoqianmingyue
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateDemoController {
	Logger log = LoggerFactory.getLogger(RestTemplateDemoController.class);
	@Autowired
	private RestTemplate restTemplate;
	 /**
     * 根据用户id 查询用户
     * @return
     */
    @Test
    public void get(){
    	
    	URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/sbe/bootUser/{id}")
				.build(1);
    	User user = restTemplate.getForObject(uri, User.class);
    	log.info("根据用户id 查询用户："+"name:{},age:{}",user.getName(),user.getAge());
    	
    	RequestEntity<Void> req = RequestEntity.get(uri)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.build();
    	ResponseEntity<User> responseEntity = restTemplate.exchange(req, User.class);
    	User user2 = responseEntity.getBody();
    	log.info("根据用户id 查询用户："+"name:{},age:{}",user2.getName(),user2.getAge());
    }
    
	 /**
     * 根据用户id 查询用户
     * @return
     */
    @Test
    public void getAll(){
    	URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/sbe/bootUser/")
				.build(1);
    	@SuppressWarnings("unchecked")
		List<User> users = restTemplate.getForObject(uri, List.class);
    	Assert.assertTrue(users!=null && users.size()>0);
    	
    	
    /*	URI uri2 = UriComponentsBuilder
				.fromUriString("http://localhost:8080/coffee/?name={name}")
				.build("mocha");*/
    	
    	ParameterizedTypeReference<List<User>> ptr =
				new ParameterizedTypeReference<List<User>>() {};
		
		ResponseEntity<List<User>> responseEntity = restTemplate
				.exchange(uri, HttpMethod.GET, null, ptr);
		List<User> body = responseEntity.getBody();
		Assert.assertTrue(users!=null && users.size()>0);
    }
    
    @Test
    public void add(){
    	String uri = "http://localhost:8080/sbe/bootUser";
    
/*//    使用下面的方式接受不到User参数 
//    	需要User 声明 @RequestBody
    	User user = new User();
    	user.setName("ljk2");
    	user.setAge(19);
    	user.setAddr("addr");
    	User userResult = restTemplate.postForObject(uri, user, User.class);
    	log.info("添加用户："+"name:{},age:{}",userResult.getName(),userResult.getAge());*/
    	
    	//postForEntity 方式获取
    	 //Form表单的都提交方式
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
         MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
         params.add("name", "ljk2");
         params.add("age", "19");
         params.add("addr", "addr");
         
         RestTemplate restTemplate = new RestTemplate();
         HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(params,headers);
         ResponseEntity<User> request = restTemplate.postForEntity(uri, httpEntity,User.class);
         User user = request.getBody();
         log.info("添加用户："+"name:{},age:{}",user.getName(),user.getAge());
         
         //postForObject 方式获取
         User userResult2 = restTemplate.postForObject(uri, httpEntity, User.class);
         log.info("添加用户："+"name:{},age:{}",userResult2.getName(),userResult2.getAge());
    }
    
    /* 
     * RestTemplate工厂类的默认实现中，不支持使用PATCH方法 。默认使用 SimpleClientHttpRequestFactory 使用标准JDK工具的实现。
     * org.springframework.web.client.ResourceAccessException: I/O error on PATCH request for "http://localhost:8080/sbe/bootUser/ljk": Invalid HTTP method: PATCH; 
     * nested exception is java.net.ProtocolException: Invalid HTTP method: PATCH
     * 
     * 解决方案：可以将默认SimpleClientHttpRequestFactory的实现改成HttpClient的 HttpComponentsClientHttpRequestFactory即可
     * 需要引入org.apache.httpcomponents httpclient的依赖
     * */
    @Test
    public void updatePatch(){
    	URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/sbe/bootUser/{name}")
				.build("ljk");
    	restTemplate.patchForObject(uri, null, Void.class);
    }
    
    @Test
    public void updatePut(){
	  String uri = "http://localhost:8080/sbe/bootUser";
	  HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      
      MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
      params.add("name", "ljk2");
      params.add("age", "19");
      params.add("addr", "addr");
      
      RestTemplate restTemplate = new RestTemplate();
      HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(params,headers);
      restTemplate.put(uri, httpEntity);
    }
    
    @Test
    public void delete(){
    	URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/sbe/bootUser/{id}")
				.build(1);
    	restTemplate.delete(uri);
    }
    
    
}
