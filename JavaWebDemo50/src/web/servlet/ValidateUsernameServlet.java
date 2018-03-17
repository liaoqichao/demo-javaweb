package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��ʾajax�û����Ƿ��Ѿ���ע���
 */
@WebServlet("/ValidateUsernameServlet")
public class ValidateUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����������Ӧ��������
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * 1.��ȡ����useranme
		 * 2.�ж��Ƿ�Ϊitcast
		 * 3.����ǣ���Ӧ1,
		 * 4.���������Ӧ0
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
