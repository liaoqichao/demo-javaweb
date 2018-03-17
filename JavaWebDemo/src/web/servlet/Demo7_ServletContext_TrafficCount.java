package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用ServletContext实现访问量统计
 * Servlet implementation class Demo7_ServletContext_TrafficCount
 */
@WebServlet("/demo7")
public class Demo7_ServletContext_TrafficCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo7_ServletContext_TrafficCount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//1.获取ServletContext对象
		//2.从ServletContext获取count属性
		//3.1 如果没有则setAttribute,并且值为1
		//3.2如果有获取属性值,count++,在setAttribute
		ServletContext application = this.getServletContext();
		Integer count = (Integer)application.getAttribute("count");
		if(count==null){
			application.setAttribute("count", 1);//这类写字符串1的话则不能count++
		}else{
			count++;
			application.setAttribute("count", count);
		}
		/*
		 * 向浏览器输出
		 * 	需要使用响应对象response
		 */
		PrintWriter pw = response.getWriter();
		pw.print("<h1>" +application.getAttribute("count")+ "</h1>");//直接用count的话第一次是null
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
