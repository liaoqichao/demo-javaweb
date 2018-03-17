package oa.state.web.servlet;

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

/**
 * Servlet implementation class PressureDocument
 */
public class PressureDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PressureDocument() {
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
		
		String cardNo = request.getParameter("addStr").toString();
		String documentHeader = request.getParameter("Header").toString();
		String nam=request.getParameter("name").toString();
		
		String no[] = cardNo.split(",");
		String name[]=nam.split(",");
		String messageContent="";
		
		Staff staff=(Staff)request.getSession().getAttribute("user");
		messageContent=staff.getDepartment()+"的"+staff.getName()+"先生：希望你快点完成"+documentHeader;
		System.out.println(messageContent);
		
		Message message=new Message();
		int i=0;
		while(i<no.length){
			message.setMessage(messageContent);
			message.setReceiveCardNo(no[i]);
			message.setReceiveName(name[i]);
			message.setSendCardNo(staff.getCardNo());
			message.setSendName(staff.getName());
			
			String uuid = CommonUtils.uuid();
			message.setMessageNo(uuid);
			message.setState(0);
			java.util.Date date = new java.util.Date();
//			java.sql.Date dataTemp = new java.sql.Date(date.getTime());
			message.setTime(date);
			
			MessageService messageService=new MessageService();
			messageService.save(message);
			i++;
		}
		
		PrintWriter pw=response.getWriter();
		pw.write("1");
		pw.close();
	}
}
