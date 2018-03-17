package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Demo17_combinedWithJSP
 */
@WebServlet({ "/Demo17_combinedWithJSP", "/demo17" })
public class Demo17_combinedWithJSP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo17_combinedWithJSP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 步骤：
		 * 1.获取JSP发送过来的参数。response.getParameter(name);或HashMap<String,String[]> map = response.getParameterMap();
		 * 2.把字符串类型变成int类型 。 Integer.parseInt(str);
		 * 3.进行加法运算；int sum = a1 + a2;
		 * 4.保存到request域中。request.setAttribute("sum",sum);
		 * 5.request.getRequestDispatcher("/jsps/demo17_result.jsp").forward(request,response);
		 */
		//1.
		String s1 = request.getParameter("int1");
		String s2 = request.getParameter("int2");
		//2.
		int num1 = Integer.parseInt(s1);
		int num2 = Integer.parseInt(s2);
		//3.
		int sum = num1 + num2;
		//4.
		request.setAttribute("sum", sum);
		//5.
		request.getRequestDispatcher("/jsps/demo17_result.jsp").forward(request, response);
	}

}
