package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示ajax，get请求方式
 */
@WebServlet("/AServlet")
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello ajax");
		response.getWriter().print("hello ajax!!!");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理编码问题
		/*
		 * CharacterEncoding:Servlet用这个编码方式读取数据(post的request用)
		 * ContentType：Servlet用这种方式发数据(response用)
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
//		response.setCharacterEncoding("utf-8");//都可以
		String username = request.getParameter("username");//获取请求参数
		String password = request.getParameter("password");
		System.out.println("(POST:) HELLO AJAX!");
		System.out.println(username+" : "+password);
		response.getWriter().print("(POST:) hello ajax!!!<br/>"+username+" : "+password);
	}

	
	
}
