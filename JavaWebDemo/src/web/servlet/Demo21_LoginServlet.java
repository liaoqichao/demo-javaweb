package web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ����
 * 1.��demo21_login.jsp���ύ�˺ź����뵽��������
 * 2.�����¼�ɹ�(�˺Ų���itcast)������demo21_succ1.jsp,session�򱣴��˺ź�����
 *   ���ʧ��,�򷵻ص�demo21_login.jsp������ʾ��¼ʧ��
 * 3.��demo21_succ1.jsp����demo21_succ2.jsp�����session�������˺ź������ʾ��¼��,ֱ������demo21_succ2.jsp
 * 	 ��������demo21_login.jsp����ʾû�е�¼��
 * 
 * Servlet implementation class Demo21_LoginServlet
 */
@WebServlet({ "/Demo21_LoginServlet", "/demo21" })
public class Demo21_LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo21_LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 0.��ȡsession�е���֤��,�ӱ���ȡ�û���д����֤�롣�������ͬ���ʹ�����Ϣ��request����,ת����login.jsp
		 * 1.������������󣬻�ȡ������
		 * 2.У���û����������Ƿ���ȷ��
		 * 3.����ɹ�,�����û���Ϣ(�û���)��Session��,Ȼ���ض���(����Ҫ��ͬһ����)��succ1.jsp.
		 * 	 ���ұ���Cookie�����û�����Ϣ,���͵��������
		 * 4.���ʧ��,���������Ϣ��request��,ת��(ͬһ������)��login.jsp
		 */
		
		//0.
		String sessionCode = (String)request.getSession().getAttribute("session_verifyCode");
		String userCode = (String)request.getParameter("verifyCode");//�û���д����֤��
		if(!sessionCode.equalsIgnoreCase(userCode)){
			request.setAttribute("msg", "��֤�����");
			//ת������Ҫ��Ŀ��
			request.getRequestDispatcher("/jsps/demo21_login.jsp").forward(request, response);
			//��Ȼ��������ִ�С�
			return;
		}
		//1.
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		@SuppressWarnings("unused")
		String psw = request.getParameter("psw");
		
		//�û������浽Cookie�б��浽�����,��һ��login.jspʱ,login���ȡrequest�е�cookie,������ʾ���ı�����
		Cookie cookie = new Cookie("user",username);
		cookie.setMaxAge(60*60*24);//����cookie����1��
		response.addCookie(cookie);//����cookie
		
		//2.
		if(!"itcast".equalsIgnoreCase(username)){//��¼�ɹ�
			//3.
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			
			//�ض���
			response.sendRedirect("/JavaWebDemo/jsps/demo21_succ1.jsp");
		} else{//��¼ʧ��
			
			//4.
			request.setAttribute("msg","�û������������!");
			RequestDispatcher rd = request.getRequestDispatcher("/jsps/demo21_login.jsp");//�õ�ת����
			rd.forward(request, response);
			
		}
	}

}
