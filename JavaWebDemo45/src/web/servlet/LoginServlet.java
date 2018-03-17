package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lqcUtils.servlet.BaseServlet;

/**
 * ��¼Servlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;


	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��ȡ�û���
		 * 2.�ж������Ƿ����admin
		 * 3.����������ǹ���Ա
		 * 4.��������ͨ��Ա
		 * 5.Ҫ�ѵ�¼���û����Ʊ��浽session����
		 * 6.ת����index.jsp
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
