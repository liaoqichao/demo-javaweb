package oa.message.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oa.message.domain.Message;
import oa.message.service.MessageService;
import oa.staff.domain.Staff;
import oa.staff.service.StaffService;

/**
 * Servlet implementation class SearchMessage
 */
public class SearchMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchMessage() {
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
		
		
		try{
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
			
			int i=0;
			JSONArray messageJSON=new JSONArray();
			Message message=null;
			StaffService staffService=new StaffService();
			Staff form=new Staff();
			while (i < messageList.size()) {
				message = messageList.get(i);
				JSONObject temp = new JSONObject();

				
				temp.put("state", message.getState());
				temp.put("time", message.getTime());
				temp.put("messageNo", message.getMessageNo());
				temp.put("message", message.getMessage());
				temp.put("receiveCardNo", message.getReceiveCardNo());
				temp.put("receiveName", message.getReceiveName());
				
				form.setCardNo(message.getSendCardNo());	
				Staff staffTemp=staffService.findById(form);
				temp.put("sendCardNo", staffTemp.getCardNo());
				temp.put("sendDepartment", staffTemp.getDepartment());
				temp.put("sendName", staffTemp.getName());
				temp.put("position", staffTemp.getPosition());
				
				messageJSON.add(temp);
				i++;
			}

			System.out.println(messageJSON);
			PrintWriter pw=response.getWriter();
			pw.print(messageJSON);
			pw.close();
		}catch(Exception e){
			PrintWriter pw=response.getWriter();
			pw.print("0");
			pw.close();
			e.printStackTrace();
		}
		
	}
}
