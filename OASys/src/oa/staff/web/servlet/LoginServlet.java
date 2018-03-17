package oa.staff.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.staff.domain.Staff;
import oa.staff.service.StaffService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StaffService staffService = new StaffService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String cardNo = request.getParameter("LoginAcc").toString();
		String password = request.getParameter("LoginPwd").toString();
		int msg = 0;

		Staff form = new Staff();
		// set cardNo，password。
		form.setCardNo(cardNo);
		form.setPassword(password);

		Staff user = staffService.validate(form);
		if (user == null) {
			msg = 0;
		} else {
			msg = 1;
		}
		request.getSession().setAttribute("user", user);

		PrintWriter out = response.getWriter();
		out.print(msg);
		out.close();
	}
}
