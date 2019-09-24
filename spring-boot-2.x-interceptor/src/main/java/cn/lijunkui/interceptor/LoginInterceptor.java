package cn.lijunkui.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{
	
	private Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	//ControllerController逻辑执行之前
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("preHandle....");
		String uri = request.getRequestURI();
		log.info("uri："+ uri);
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			log.info("拦截 Controller："+ handlerMethod.getBean().getClass().getName());
			log.info("拦截方法："+handlerMethod.getMethod().getName());
		}
		
		return true;
	}
	
	//Controller逻辑执行完毕但是视图解析器还为进行解析之前
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    	log.info("postHandle....");
    	Map<String,Object>map=modelAndView.getModel();
		map.put("msg","postHandle add msg");
    }
    
    //Controller逻辑和视图解析器执行完毕
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    	log.info("afterCompletion....");
    }
}
