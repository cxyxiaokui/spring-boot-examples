package cn.lijunkui.interceptor;

import java.util.Date;
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
import cn.lijunkui.exception.message.ReturnMessage;
import cn.lijunkui.exception.message.ReturnMessageUtil;
import cn.lijunkui.jwt.Payload;
import cn.lijunkui.utils.JWTService;
import cn.lijunkui.utils.JsonUtil;


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
			 Payload payload = jwtService.verifyToken(token);
			 if(isExpire(payload)) {
			     String newToken = jwtService.createToken(payload.getClaims(), 1);
			     
			     ReturnMessage<Object> message = buildReturnMessage(newToken);
			     String messageJson = JsonUtil.toJson(message);
			     response.getWriter().write(messageJson);
				 return false;
			 }
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
	
	private ReturnMessage<Object> buildReturnMessage(String newToken) {
		ReturnMessage<Object> message = ReturnMessageUtil.sucess(newToken);
		message.setMessage("需要更换Token!");
		return message;
	}

	/**
	 * 半个小时后更换一次 Token
	 * @param payload
	 * @return
	 */
	private boolean isExpire(Payload payload) {
		Date currentDate = new Date();
		Date expiresAt = payload.getExpiresAt();
		Date issuedAt = payload.getIssuedAt();
		Date afterDateByMinute = jwtService.getAfterDateByMinute(issuedAt, 30);
		
		if (afterDateByMinute.getTime() <= currentDate.getTime() && currentDate.getTime() <= expiresAt.getTime()) {
			return true;
		}
		return false;
	}

	@Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
