package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示请求转发和请求包含
 * Servlet implementation class Demo14_HttpServletRequest_RequestDispatcher_a
 */
@WebServlet({ "/Demo14_HttpServletRequest_RequestDispatcher_a", "/demo14_a" })
public class Demo14_HttpServletRequest_RequestDispatcher_a extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo14_HttpServletRequest_RequestDispatcher_a() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AServlet");
		/**
		 * AServlet设置响应头和响应体A
		 * AServlet请求转发到BServlet
		 * BServlet设置响应体B
		 * 结果：AServlet有发送响应头,浏览器输出响应体B(或者抛异常),而浏览器的地址栏还是demo14_a
		 * 抛出异常的情况：AServlet做的事太多,能做那么多事还干嘛转发？(内部有缓存机制)
		 * 	例如:for(int i=0 ; i < 1024 *24 +1 ;i++){
		 * 			response.getWriter().print("a");
		 * 		}
		 * 	//页面输入很多a,然后控制台抛出异常
		 * 
		 * A请求B,B请求C,...,能写响应体的只有最右一个Servlet
		 */
		//1.设置响应头和响应体
		response.setHeader("AServletSetHeader", "A");	//设置响应头
		response.getWriter().print("hello AServlet");		//设置响应体
		
		/**
		 * 向request域中添加属性,必须放在转发之前
		 */
		request.setAttribute("user", "zhangsan");
		
		//2.请求转发或请求包含
		//请求转发。等于调用BServlet的sevice()方法
		request.getRequestDispatcher("/demo14_b").forward(request, response);
		//浏览器显示hello AServlet
		
		//请求包含
//		request.getRequestDispatcher("/demo14_b").include(request, response);
		//浏览器显示：hello AServlethello BServlet
		
	}

}
