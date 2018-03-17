package oa.message.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.message.domain.Message;
import oa.message.service.MessageService;

/**
 * Servlet implementation class SendMessage
 */
public class ChangeMessageState extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeMessageState() {
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

		String messageNo = request.getParameter("messageNo").toString();
		System.out.println(messageNo);
		
		MessageService messageService=new MessageService();
		Message message=messageService.findByMessageNo(messageNo);
		
		message.setState(1);
		PrintWriter pw=response.getWriter();
		try{
			messageService.update(message);
			pw.write("1");
			pw.close();
		}catch(Exception e){
			pw.write("0");
			pw.close();
			e.printStackTrace();
		}
		
	}

}
