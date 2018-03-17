package oa.issue.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oa.issue.service.IssueService;
import oa.staff.domain.Staff;
import oa.staff.service.StaffService;

/**
 * Servlet implementation class Del_issue
 */
public class DelIssue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StaffService staffService = new StaffService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DelIssue() {
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

		String cardNo = request.getParameter("delStr").toString();
		int msg = 0;

		Staff form = new Staff();// 声明变量form查询数据
		form.setCardNo(cardNo);

		Staff user = staffService.findById(form);// 声明变量user保存数据，调用staffservice
		IssueService is = new IssueService();
		try {
			is.remove(user);
			msg = 1;
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
			msg = 0;
		} // IssueService类，remove方法里调用dao里的方法实现删除

		PrintWriter out = response.getWriter();
		out.print(msg);
		out.close();
	}

}
