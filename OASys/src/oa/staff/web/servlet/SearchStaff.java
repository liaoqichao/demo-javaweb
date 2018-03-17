package oa.staff.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import oa.staff.domain.Staff;
import oa.staff.service.StaffService;

/**
 * Servlet implementation class SearchDepartment
 */
public class SearchStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchStaff() {
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

		Staff staff=(Staff)request.getSession().getAttribute("user");

		System.out.println(staff.getCardNo());
		
		StaffService staffService = new StaffService();
		List<Staff> staffList = staffService.findByDepartment(staff.getDepartment());

		String staffJson = JSONArray.fromObject(staffList).toString();// json
		System.out.println(staffJson);

		PrintWriter out = response.getWriter();
		out.print(staffJson);
		out.close();
	}

}
