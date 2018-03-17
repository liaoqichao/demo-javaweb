package web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Demo4_ServletContext_a
 * ��ʾServletContext�б�������
 */
@WebServlet("/Demo4_ServletContext_a")
public class Demo4_ServletContext_a extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*
	 * 1.��ȡServletContext����
	 * 2.����setAttribute������������
	 */
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo4_ServletContext_a() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		1.��ȡServletContext����
		ServletContext application = this.getServletContext();
//		2.����setAttribute������������
		application.setAttribute("name", "����");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
