package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��ʾajax��get����ʽ
 */
@WebServlet("/AServlet")
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello ajax");
		response.getWriter().print("hello ajax!!!");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����������
		/*
		 * CharacterEncoding:Servlet��������뷽ʽ��ȡ����(post��request��)
		 * ContentType��Servlet�����ַ�ʽ������(response��)
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
//		response.setCharacterEncoding("utf-8");//������
		String username = request.getParameter("username");//��ȡ�������
		String password = request.getParameter("password");
		System.out.println("(POST:) HELLO AJAX!");
		System.out.println(username+" : "+password);
		response.getWriter().print("(POST:) hello ajax!!!<br/>"+username+" : "+password);
	}

	
	
}
