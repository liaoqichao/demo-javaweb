package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ����������302�ض���,������Locationͷ
 * 1.�û�����AServlet
 * 2.AServlet��Ӧ302,����Locationͷ
 * 3.������ض���BServlet
 * 4.BServlet��Ӧ
 * 
 * ������ڵ�ַ������http://localhost:8080/JavaWebDemo/demo10_a
 * 	   ����http://localhost:8080/JavaWebDemo/demo10_b
 * Servlet implementation class Demo10_HttpServletResponse_AServlet
 */
@WebServlet("/demo10_a")
public class Demo10_HttpServletResponse_AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo10_HttpServletResponse_AServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AServlet...");
		//1.����Location
//		response.setHeader("Location", "/JavaWebDemo/demo10_b");
		// /��Ŀ��/Servlet·����(web.xml�е�url-pattern) ��������������URI
		//2.����302״̬��
//		response.setStatus(302);
		
		
		//��ݷ�������ض���sendRedirect(String location);
		response.sendRedirect("/JavaWebDemo/demo10_b");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
