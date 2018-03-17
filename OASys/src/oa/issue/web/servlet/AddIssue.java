package oa.issue.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.issue.service.IssueException;
import oa.issue.service.IssueService;
import oa.staff.domain.Staff;
import oa.staff.service.StaffService;

/**
 * Servlet implementation class AddIssue
 */
public class AddIssue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddIssue() {
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
		request.setCharacterEncoding("UTF-8");

		String cardNo = request.getParameter("cardNo");
		String name = request.getParameter("name");
		String department = request.getParameter("department");
		String position = request.getParameter("position");
		String msg = "";
		boolean authority=false;

		Staff user=(Staff)request.getSession().getAttribute("user");
		IssueService issueService = new IssueService();
		List<Staff>staffList=issueService.findByDepartment(department);
		int i=0;
		while(i<staffList.size()){
			if(user.getCardNo().equals(staffList.get(i).getCardNo())){
//				System.out.println("abcsddff");
				authority=true;
				break;
			}
			i++;
		}
		
		if(authority==false){
			msg = "<script type='text/javascript'>alert('您无权限行此操作');</script>";
		}
		else if (cardNo.equals("") || name.equals("") || department.equals("") || position.equals("")) {
			System.out.println("空字符");
			msg = "<script type='text/javascript'>alert('请输入完整的信息');</script>";
		} else {
			StaffService staffService = new StaffService();

			Staff form = new Staff();
			form.setCardNo(cardNo);

			Staff staff = staffService.findById(form);

			if (staff.getName().equals(name) && staff.getDepartment().equals(department)
					&& staff.getPosition().equals(position)) {
				IssueService is = new IssueService();
				try {
					is.add(form);
					msg = "<script type='text/javascript'>alert('添加成功');</script>";
				} catch (IssueException e) {
					msg=e.getMessage();
					request.setAttribute("message", msg);
					e.printStackTrace();
				}
			} else {
				msg = "<script type='text/javascript'>alert('信息不正确');</script>";
			}
		}
		request.setAttribute("message", msg);
		this.getServletConfig().getServletContext().getRequestDispatcher("/issue/add_issue.jsp").forward(request,
				response);
	}
}
