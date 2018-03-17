package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示响应XML
 */
@WebServlet("/BServlet")
public class BServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String xml ="<students>" +
				"<student number='ITCAST_0001'>" +
				"<name>张三</name>" +
				"<age>18</age>" +
				"<sex>male</sex>" +
				"</student>" +
				"</students>";
		response.setContentType("text/xml;charset=utf-8");//需要设置ContentType为xml
		response.getWriter().print(xml);
	}

}
