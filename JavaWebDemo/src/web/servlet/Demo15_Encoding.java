package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Demo15_Encoding
 */
@WebServlet({ "/Demo15_Encoding", "/demo15" })
public class Demo15_Encoding extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo15_Encoding() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 获取请求参数
		 * 1.先获取乱码字符串
		 * 2.重新ISO编码
		 * 3.用UTF-8解码
		 * 
		 * 给浏览器响应
		 * 1.在getWriter()之前response.setContentType("text/html;charset=utf-8");
		 */
		String username = request.getParameter("user");
//		byte[] bytes = username.getBytes("iso-8859-1");
//		username = new String(bytes, "utf-8");
//		不需要的原因：默认就是UTF-8而不是ISO-8859-1. 为什么SERVER.XML找不到相关配置?
		System.out.println(username);
		
		response.setContentType("text/html;charset=utf-8");	//没有这句出现乱码
		response.getWriter().print("响应...");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 获取请求参数
		 * 1.在获取参数之前先调用request.setCharacterEncoding("utf-8");
		 * 2.使用getParameter()获取参数
		 * 
		 * 给浏览器响应
		 * 1.在getWriter()之前response.setContentType("text/html;charset=utf-8");
		 */
		request.setCharacterEncoding("utf-8");//没有这句6个问号。??????
		String username = request.getParameter("user");
		System.out.println(username);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("响应...");
	}

}
