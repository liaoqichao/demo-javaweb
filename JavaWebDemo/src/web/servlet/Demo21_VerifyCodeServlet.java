package web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myUtils.VerifyCode;


/**
 * Servlet implementation class Demo21_VerifyCodeServlet
 */
@WebServlet({ "/Demo21_VerifyCodeServlet", "/demo21_verifyCode" })
public class Demo21_VerifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo21_VerifyCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 1.����ͼƬ
		 * 2.����ͼƬ�ϵ��ı���session����
		 * 3.��ͼƬ��Ӧ���ͻ���
		 */
		//1.
		VerifyCode v = new VerifyCode();
		BufferedImage bi = v.getImage();
		
		//2.
		HttpSession session = request.getSession();
		session.setAttribute("session_verifyCode", v.getText());
		
		//3.
		v.output(bi, response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * ***JavaWeb����Ҫ��jar�����Ƶ�/WEB-INF/lib
		 * 1.����ͼƬ
		 * 2.����ͼƬ�ϵ��ı���session����
		 * 3.��ͼƬ��Ӧ���ͻ���
		 */
		//1.
		VerifyCode v = new VerifyCode();
		BufferedImage bi = v.getImage();
		
		//2.
		HttpSession session = request.getSession();
		session.setAttribute("session_verifyCode", v.getText());
		
		//3.
		v.output(bi, response.getOutputStream());
	}

}
