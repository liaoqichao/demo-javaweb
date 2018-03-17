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
     * 通过request获得与请求url相关的信息
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
		//360浏览器把<br/>当成字符串直接输出了,用IE
		response.getWriter().print(request.getScheme() + "<br/>");	//获取请求协议
		response.getWriter().print(request.getServerName() + "<br/>");//获取服务器名称
		response.getWriter().println(request.getServerPort() + "<br/>");	//获取端口号
		response.getWriter().println(request.getContextPath() + "<br/>");	//获取项目名（上下文路径） 最常用
		response.getWriter().println(request.getServletPath() + "<br/>");	//获取Servlet路径
		response.getWriter().println(request.getQueryString() + "<br/>");	//获取参数部分
		response.getWriter().println(request.getRequestURI() + "<br/>");	//获取URL=/项目名/Servlet路径
		response.getWriter().println(request.getRequestURL() + "<br/>");	//URL问号前面部分
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
