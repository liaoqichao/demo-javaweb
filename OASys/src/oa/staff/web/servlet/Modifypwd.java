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
 * Servlet implementation class SearchInfo
 */
public class Modifypwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifypwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request,response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");

				String pwd=request.getParameter("pwd");
				String newPwd=request.getParameter("newPwd");
				String msg="";
				System.out.println(pwd+"  "+newPwd);
				
				StaffService staffService=new StaffService();
				PrintWriter out =response.getWriter();
				
				Staff staff=(Staff)request.getSession().getAttribute("user");
				try{
					staff.getCardNo();
				}catch(Exception e){
					msg="您还未登录";
					out.print(msg);
					out.close();
					e.printStackTrace();
				}
				
				try{
					if(pwd.equals(staff.getPassword())){
						staffService.modifyPassword(staff.getCardNo(), newPwd);
						msg="密码修改成功";
					}
					else{
						msg="原密码输入错误";
					}
				}catch(Exception e){
					msg="修改密码发生了错误，请重试";
					out.print(msg);
					out.close();
					e.printStackTrace();
				}
				out.print(msg);
				out.close();		
			}
}