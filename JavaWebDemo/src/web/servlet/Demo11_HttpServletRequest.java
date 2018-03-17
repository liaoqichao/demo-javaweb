package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取请求ip和User-Agent请求头：
 * 获取客户端的ip,请求方式,根据User-Agent请求头获取操作系统和浏览器信息
 * Servlet implementation class Demo11_HttpServletRequest_getUserAgent
 * 
 * Referer请求头：防盗链
 */
@WebServlet("/demo11")
public class Demo11_HttpServletRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo11_HttpServletRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
		 * 获取请求ip,
		 * 获取User-Agent信息
		 */
//		String ip = request.getRemoteAddr();
//		System.out.println("ip : "+ip);						//获取ip地址
//		System.out.println("请求方式:"+request.getMethod());	//获取请求方式,只有表单的method="post"才是POST方式
//		String userAgent = request.getHeader("User-Agent");	//获取名为User-Agent请求头的信息
//		System.out.println(userAgent);
//		if(userAgent.toLowerCase().contains("msie")){
//			System.out.println("你用的是IE浏览器内核的浏览器");
//		}else if(userAgent.toLowerCase().contains("chrome")){
//			System.out.println("你用的是谷歌浏览器内核的浏览器");
//		}else if(userAgent.toLowerCase().contains("firefox")){
//			System.out.println("你用的是火狐浏览器内核的浏览器");
//		}
		
		/**
		 * Referer：防盗链
		 * 从http://localhost:8080/JavaWebDemo/demo11.html或者url不包括localhost进入,则进入成功
		 * 如果直接在地址栏输入http://localhost:8080/JavaWebDemo/demo11会跳到主页
		 */
		String referer = request.getHeader("Referer");//如果直接从地址栏输入地址,则值为null	
		System.out.println(referer);//http://localhost:8080/JavaWebDemo/demo11.html
		if(referer == null || !referer.contains("localhost")){
			response.sendRedirect("/JavaWebDemo/index.jsp");//重定向
		}else if(referer.contains("localhost")){
			System.out.println("hello,from localhost!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
