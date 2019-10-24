package cn.lijunkui.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import cn.lijunkui.exception.customexception.JKException;
import cn.lijunkui.exception.message.CodeEnum;
import cn.lijunkui.utils.JWTService;


public class LoginInterceptor implements HandlerInterceptor{
	
	Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	private JWTService jwtService;
	
	public LoginInterceptor(JWTService jwtService) {
		this.jwtService = jwtService;
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		
		log.info("Token Checkout processing");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");

		String token = request.getParameter("token");
		
		if (StringUtils.isEmpty(token)) {
			throw new JKException(CodeEnum.TOKENISEMPTY);
		}
		
		String tokenInServletContext = (String)request.getServletContext().getAttribute(token);
		if(StringUtils.isEmpty(tokenInServletContext)) {
			throw new JKException(CodeEnum.ILLEGALTOKEN);
		}
		
		try {
			 jwtService.verifyToken(token);
		} catch (AlgorithmMismatchException  e) {
			log.error("Token Checkout processing AlgorithmMismatchException 异常！"+e.getLocalizedMessage());
			throw new JKException(CodeEnum.ILLEGALTOKEN);
		}catch (TokenExpiredException  e) {
			log.info("token已经过期");
			throw new JKException(CodeEnum.EXPIRETOKEN);
		}catch (SignatureVerificationException  e) {
			log.error("Token Checkout processing SignatureVerificationException 异常！"+e.getLocalizedMessage());
			throw new JKException(CodeEnum.ILLEGALTOKEN);
		 }catch (Exception e) {
			log.error("Token Checkout processing 未知异常！"+e.getLocalizedMessage());
			throw e;
		}
		
		return true;
	}

	@Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
