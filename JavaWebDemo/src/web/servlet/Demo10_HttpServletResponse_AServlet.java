package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案例：发送302重定向,并设置Location头
 * 1.用户请求AServlet
 * 2.AServlet响应302,给出Location头
 * 3.浏览器重定向到BServlet
 * 4.BServlet响应
 * 
 * 结果：在地址栏输入http://localhost:8080/JavaWebDemo/demo10_a
 * 	   跳到http://localhost:8080/JavaWebDemo/demo10_b
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
		//1.设置Location
//		response.setHeader("Location", "/JavaWebDemo/demo10_b");
		// /项目名/Servlet路径名(web.xml中的url-pattern) 合起来叫做请求URI
		//2.发送302状态码
//		response.setStatus(302);
		
		
		//快捷方法完成重定向：sendRedirect(String location);
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
