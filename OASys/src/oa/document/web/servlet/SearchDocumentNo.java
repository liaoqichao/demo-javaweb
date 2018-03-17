package oa.document.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oa.document.domain.State;
import oa.document.service.StateException;
import oa.document.service.StateService;
import oa.staff.domain.Staff;

/**
 * Servlet implementation class SearchDocument
 */
public class SearchDocumentNo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchDocumentNo() {
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

		String documentHeader = request.getParameter("Header").toString();
		String state = request.getParameter("state").toString();

		System.out.println(documentHeader + "  " + state);

		List<State> stateList = new ArrayList<State>();

		Staff staff=(Staff) request.getSession().getAttribute("user");

		StateService stateService = new StateService();
		if (documentHeader.equals("")) {
			if (state.equals("0")) {
				List<State> A = stateService.findByState("0", staff.getCardNo());
				List<State> B = stateService.findByState("5", staff.getCardNo());
				stateList.addAll(A);
				stateList.addAll(B);
			} else if (state.equals("5")) {
				List<State> A = stateService.findByState("5", staff.getCardNo());
				stateList.addAll(A);
			} else {
				List<State> A = stateService.findByState("1", staff.getCardNo());
				List<State> B = stateService.findByState("2", staff.getCardNo());
				List<State> C = stateService.findByState("3", staff.getCardNo());
				List<State> D = stateService.findByState("6", staff.getCardNo());
				List<State> E = stateService.findByState("7", staff.getCardNo());

				stateList.addAll(A);
				stateList.addAll(B);
				stateList.addAll(D);
				stateList.addAll(C);
				stateList.addAll(E);
			}

		} else {
			try {
				stateList = stateService.findByDocumentHeader(staff.getCardNo(), documentHeader);
			} catch (StateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(stateList);
		PrintWriter out = response.getWriter();
		out.print(stateList.size());
		out.close();
	}
}
