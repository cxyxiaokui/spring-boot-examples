package cn.lijunkui.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import cn.lijunkui.model.User;
import cn.lijunkui.utils.JsonUtil;

import com.google.gson.Gson;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User user1 = new User("ljk1",18);
		User user2 = new User("ljk1",18);
		List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		
		String userListJson = JsonUtil.toJson(userList);
		resp.getWriter().write(userListJson);
		return ;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
	@Override
	public void destroy() {
	}

}
