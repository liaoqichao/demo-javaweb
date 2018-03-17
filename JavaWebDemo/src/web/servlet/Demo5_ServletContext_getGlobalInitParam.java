package web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ʹ��ServletContext��ȡ������ʼ������
 * ��url�����ʱ��Ҫ����/Demo5_ServletContext_getGlobalInitParam �����Ǹ�url-pattern
 * Servlet implementation class Demo5_ServletContext_getGlobalInitParam
 */
@WebServlet("/Demo5_ServletContext_getGlobalInitParam")
public class Demo5_ServletContext_getGlobalInitParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo5_ServletContext_getGlobalInitParam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//1.
		ServletContext application = this.getServletContext();
		//2.����getInitParameter(String name)�õ���ʼ������
		String value = application.getInitParameter("KEY");
		System.out.println(value);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
