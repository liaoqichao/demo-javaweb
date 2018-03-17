package demo13.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo13.service.MobileCodeWS;
import demo13.service.MobileCodeWSSoap;

/**
 * Servlet implementation class PhoneServlet
 */
@WebServlet("/PhoneServlet")
public class PhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 解决编码问题
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 2. 获取请求参数
		 */
		String number = request.getParameter("num");
		/*
		 * 3. 调用服务
		 */
		MobileCodeWS ws = new MobileCodeWS();
		MobileCodeWSSoap soap = ws.getMobileCodeWSSoap12();
		String str = soap.getMobileCodeInfo(number, null);
		System.out.println(str);
		
		/*
		 * 4.返回结果，使用ajax所以不是请求转发
		 */
		response.getWriter().print(str);
	}

}
