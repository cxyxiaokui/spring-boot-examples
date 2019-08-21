package cn.lijunkui.swagger;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//让Spring来加载该类配置
@Configuration
//是否禁用swagger 的配置
@ConditionalOnProperty(prefix = "swagger",value = {"enable"},havingValue = "true")
@EnableSwagger2 //启用Swagger2
public class SwaggerConfig {
	 @Bean
	    public Docket alipayApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("简单用户管理API接口文档")
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("cn.lijunkui.swagger"))
	                //.paths(PathSelectors.regex("/user/updateUserByRequestBody")
	                //.paths(PathSelectors.regex("/user/*")
	                .paths(PathSelectors.ant("/user/**")
	                		).build();
	    }
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("SprignBoot学习专栏")
	                .description("集成swagger")
	                .termsOfServiceUrl("https://blog.csdn.net/ljk126wy")
	                //创建人
	                .contact(new Contact("桌前明月", "http://www.baidu.com", ""))
	                //版本
	                .version("1.0")
	                //API 描述
	                .description("简单介绍如有问题还望指正")//
	                .build();
	    }
	    public static void main(String[] args) {
	    	
	    	String str = "/user/*";
	    System.out.println(str.matches("/user/updateUserByRequestBody"));
		}
}
