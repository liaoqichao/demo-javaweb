package bookstore.user.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lqcUtils.VerifyCode;

/**
 * ��֤���Servlet
 */
@WebServlet("/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VerifyCode vcode = new VerifyCode();
		vcode.output(vcode.getImage(), response.getOutputStream());//��ͼƬ��Ӧ������ҳ�档
		request.getSession().setAttribute("session_vcode", vcode.getText());//�����ı�
		/*
		 * �����ͼƬ��Ӧ��ҳ������ͱ����ı���˳��ߵ����ᵼ��session������ı�ΪΪ��һ����ȷ����֤���ı���
		 * û�е���vcode.getImage()������û�д�����ͼƬ��û���µ����֡�
		 */
	}

}
