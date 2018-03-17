package demo33.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo33.domain.User;
import demo33.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet({ "/UserServlet", "/demo33", "/user" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//WEB层依赖于业务逻辑层
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserService userService = new UserService();
		User user = userService.find();
		//添加到域
		request.setAttribute("user", user);
		//转发到JSP
		request.getRequestDispatcher("/show.jsp").forward(request, response); 
	}

}
