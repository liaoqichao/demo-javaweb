package oa.staff.web.servlet;

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
 * Servlet implementation class DelStaff
 */
public class DelStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelStaff() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String delStr=request.getParameter("delStr").toString();
		String no[]=delStr.split(",");
		String department=request.getParameter("department").toString();
		String message="";
		
		System.out.println(delStr+" "+department);
		
		Staff staff=(Staff)request.getSession().getAttribute("user");
		StaffService staffService=new StaffService();
		IssueService issueService=new IssueService();
		Staff form=new Staff();
		
		try{
			PrintWriter out=response.getWriter();
			if(staff.getDepartment().equals(department)){
				int i=0;
				while(i<no.length){
					form.setCardNo(no[i]);
					if(issueService.get(form.getCardNo())!=null){		//issueService 对stafftable 进行了一次完整的查找，要先删除附表，在删主表
						issueService.remove(form);
					}
					staffService.delete(form.getCardNo());  //form为Staff类型，cancel方法根据卡号删除记录。
					i++;
				}
				message="删除成功";
			}
			else{
				message="你无权限删除这些职员";
			}
			
			out.write(message);
			out.close();
		}catch(Exception e){
			message="删除失败";
			PrintWriter out=response.getWriter();
			out.write(message);
			out.close();
			e.printStackTrace();
		}
		
	}

}
