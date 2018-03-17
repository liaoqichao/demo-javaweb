package demo36.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BServlet
 */
@WebServlet("/BServlet")
public class BServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String fun1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("fun1()..");
		return "/index.jsp";
	}
	public String fun2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("fun2()..");
		return "r:/index.jsp";
	}
	public String fun3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("fun3()..");
		return "e:/-_,-";//´íÎóÇ°×º
	}

}
