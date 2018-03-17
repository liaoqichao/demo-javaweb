package oa.message.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.message.domain.Message;
import oa.message.service.MessageService;
import oa.staff.domain.Staff;

/**
 * Servlet implementation class SearchMessage
 */
public class SearchMessageNo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchMessageNo() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		String messageNo = request.getParameter("messageNo").toString();
		String name = request.getParameter("name").toString();
		String state=request.getParameter("state").toString();

		System.out.println(name + " " + messageNo+" state:"+state);
		
		Staff staff=(Staff) request.getSession().getAttribute("user");
		
		MessageService messageService=new MessageService();
		List<Message>messageList=null;
		if(state.equals("1")){						//JSP,1未接收，但是在数据库里0才是未接收
			messageList=messageService.findByReceiveCardNoState(staff.getCardNo(),0);
		}
		else{
			if (messageNo.equals("-1")) {
				if(name.equals("")){
					messageList=messageService.findByReceiveCardNo(staff.getCardNo());
				}
				else{
					messageList=messageService.findByReceiveSendCardNo(name,staff.getCardNo());
				}
			} 
			else {
				messageList=(List<Message>) messageService.findByMessageNo(messageNo);
			}
		}
		System.out.println(messageList);
		PrintWriter pw=response.getWriter();
		pw.print(messageList.size());
		pw.close();
		}
}
