package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lqcUtils.servlet.BaseServlet;

/**
 * 登录Servlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取用户名
		 * 2.判断名字是否包含admin
		 * 3.如果包含则是管理员
		 * 4.否则是普通会员
		 * 5.要把登录的用户名称保存到session域中
		 * 6.转发到index.jsp
		 */
		//1.
		String username  = request.getParameter("username");
		//2.
		if(username.contains("admin")){//3.
			//5.
			request.getSession().setAttribute("admin", username);
		} else{//4.
			//5.
			request.getSession().setAttribute("user", username);
		}
		//6.
		return "f:/index.jsp";
	}

}
