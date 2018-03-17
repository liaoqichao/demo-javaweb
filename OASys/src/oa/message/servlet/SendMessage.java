package oa.message.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ComonUtils.CommonUtils;
import oa.message.domain.Message;
import oa.message.service.MessageService;
import oa.staff.domain.Staff;
import oa.staff.service.StaffService;

/**
 * Servlet implementation class SendMessage
 */
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendMessage() {
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		String sendCardNo = request.getParameter("sendCardNo").toString();
		String content=request.getParameter("message").toString();
		
		String no[]=sendCardNo.split(";");

		Staff staff=(Staff) request.getSession().getAttribute("user");
		
		Message message=new Message();
		MessageService messageService=new MessageService();
		StaffService staffService=new StaffService();
		Staff form=new Staff();
		Staff receiver=null;
		int i=0;
		
		PrintWriter pw=response.getWriter();
		try{
			while(i<no.length){
				message.setMessage(content);
				
				form.setCardNo(no[i]);
				receiver=staffService.findById(form);
				message.setReceiveCardNo(receiver.getCardNo());
				message.setReceiveName(receiver.getName());
				
				message.setSendCardNo(staff.getCardNo());
				message.setSendName(staff.getName());
				
				String uuid = CommonUtils.uuid();
				message.setMessageNo(uuid);
				message.setState(0);
				java.util.Date date = new java.util.Date();
//				java.sql.Date dataTemp = new java.sql.Date(date.getTime());
				message.setTime(date);
				
				messageService.save(message);
				
				i++;
			}
			pw.write("1");
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
			pw.write("0");
			pw.close();
		}
	}

}
