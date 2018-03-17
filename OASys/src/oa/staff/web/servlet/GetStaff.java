package oa.staff.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import oa.staff.domain.Staff;
import oa.staff.service.StaffService;

/**
 * Servlet implementation class GetStaff
 */
public class GetStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetStaff() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String cardNo = request.getParameter("searchCardNo").toString();
		
		Staff staff=null;
		if(cardNo.equals("-1")){
			staff=(Staff)request.getSession().getAttribute("user");
			
		}
		else{
			Staff form = new Staff();
			form.setCardNo(cardNo);

			StaffService staffService = new StaffService();
			staff = staffService.findById(form);
		}
		System.out.println(staff);
		PrintWriter out = response.getWriter();
		if (staff == null) {
			out.print("0");
			out.close();
		} else {
			JSONArray js=new JSONArray();
			js.add(0, staff);
			out.print(js);
			out.close();
		}
	}

}
