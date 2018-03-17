package oa.state.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.document.domain.State;
import oa.document.service.StateService;

/**
 * Servlet implementation class SearchDocument
 */
public class ChangeState extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeState() {
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

		String stateNo = request.getParameter("stateNo").toString();
		String state = request.getParameter("state").toString();

		System.out.println(stateNo);

		StateService stateService = new StateService();
		State temp = stateService.findByStateNo(stateNo);
		temp.setState(state);
		java.util.Date date = new java.util.Date();
//		java.sql.Date dataTemp = new java.sql.Date(date.getTime());
		temp.setTime(date);
		
		stateService.update(temp);
		
		PrintWriter out = response.getWriter();
		out.print("1");
		out.close();
	}
}
