package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Demo12_HttpServletRequest_getURLinfo
 */
@WebServlet("/demo12")
public class Demo12_HttpServletRequest_getURLinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * ͨ��request���������url��ص���Ϣ
     * @see HttpServlet#HttpServlet()
     */
    public Demo12_HttpServletRequest_getURLinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//360�������<br/>�����ַ���ֱ�������,��IE
		response.getWriter().print(request.getScheme() + "<br/>");	//��ȡ����Э��
		response.getWriter().print(request.getServerName() + "<br/>");//��ȡ����������
		response.getWriter().println(request.getServerPort() + "<br/>");	//��ȡ�˿ں�
		response.getWriter().println(request.getContextPath() + "<br/>");	//��ȡ��Ŀ����������·���� ���
		response.getWriter().println(request.getServletPath() + "<br/>");	//��ȡServlet·��
		response.getWriter().println(request.getQueryString() + "<br/>");	//��ȡ��������
		response.getWriter().println(request.getRequestURI() + "<br/>");	//��ȡURL=/��Ŀ��/Servlet·��
		response.getWriter().println(request.getRequestURL() + "<br/>");	//URL�ʺ�ǰ�沿��
//		http://localhost:8080/JavaWebDemo/demo12
//		http
//		localhost
//		8080
//		/JavaWebDemo
//		/demo12
//		null
//		/JavaWebDemo/demo12
//		http://localhost:8080/JavaWebDemo/demo12

	}

}
