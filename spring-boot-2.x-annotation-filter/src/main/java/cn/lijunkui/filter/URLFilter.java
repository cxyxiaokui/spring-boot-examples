package cn.lijunkui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(urlPatterns="/*")
public class URLFilter implements Filter{
	
	private Logger log = LoggerFactory.getLogger(URLFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServeltRequest = (HttpServletRequest)request;
		String requestURI = httpServeltRequest.getRequestURI();
		log.info("访问地址："+requestURI);
		chain.doFilter(request, response);
	}

}
