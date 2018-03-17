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
 * 演示ServletContext中保存数据
 */
@WebServlet("/Demo4_ServletContext_a")
public class Demo4_ServletContext_a extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*
	 * 1.获取ServletContext对象
	 * 2.调用setAttribute方法保存数据
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
//		1.获取ServletContext对象
		ServletContext application = this.getServletContext();
//		2.调用setAttribute方法保存数据
		application.setAttribute("name", "张三");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
