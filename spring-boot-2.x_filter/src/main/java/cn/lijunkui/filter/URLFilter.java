package cn.lijunkui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class URLFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServeltRequest = (HttpServletRequest)request;
		String requestURI = httpServeltRequest.getRequestURI();
		StringBuffer requestURL = httpServeltRequest.getRequestURL();
		System.out.println("requestURI:"+requestURI +" requestURL："+requestURL);
		chain.doFilter(request, response);
	}
}
