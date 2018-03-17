package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Demo14_HttpServletRequest_RequestDispatcher_b
 */
@WebServlet({ "/Demo14_HttpServletRequest_RequestDispatcher_b", "/demo14_b" })
public class Demo14_HttpServletRequest_RequestDispatcher_b extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo14_HttpServletRequest_RequestDispatcher_b() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BServlet");
		/**
		 * 获取AServlet设置的属性
		 */
		String user = (String)request.getAttribute("user");
		//3.设置响应体
		response.getWriter().println("hello BServlet" + user);//hello BServletzhangsan
	}

}
