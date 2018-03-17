package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示ajax用户名是否已经被注册过
 */
@WebServlet("/ValidateUsernameServlet")
public class ValidateUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理请求和相应编码问题
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * 1.获取参数useranme
		 * 2.判断是否为itcast
		 * 3.如果是，响应1,
		 * 4.如果不是响应0
		 */
		//1.
		String username = request.getParameter("username");
		//2.
		if(username.equals("itcast")){
			//3.
			response.getWriter().print("1");
		} else{	//4.
			response.getWriter().print("0");
		}
		
		
	}

}
