package oa.state.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oa.document.domain.State;
import oa.document.service.StateException;
import oa.document.service.StateService;
import oa.staff.domain.Staff;
import oa.staff.service.StaffService;

/**
 * Servlet implementation class SearchState
 */
public class SearchState extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchState() {
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

		String header = request.getParameter("documentHeader").toString();
		System.out.println(header);

		PrintWriter out = response.getWriter();
		StateService stateService = new StateService();
		StaffService staffService = new StaffService();
		Staff stafftemp = null;
		try {
			List<State> stateList = stateService.findByDocumentHeader(header);
			JSONArray stateJson = new JSONArray();
			int i = 0;
			while (i < stateList.size()) {
				JSONObject temp = new JSONObject();

				State stateTemp = stateList.get(i);
				temp.put("state", stateTemp.getState());
				temp.put("time", stateTemp.getTime());
				temp.put("stateNo", stateTemp.getStateNo());
				temp.put("documentHeader", stateTemp.getDocumentHeader());

				Staff form = new Staff();
				form.setCardNo(stateTemp.getCardNo());
				stafftemp = staffService.findById(form);
				temp.put("receiver_cardNo", stafftemp.getCardNo());
				temp.put("receiver_name", stafftemp.getName());
				temp.put("receiver_department", stafftemp.getDepartment());
				temp.put("receiver_position", stafftemp.getPosition());
				
				form.setCardNo(stateTemp.getSendCardNo());
				stafftemp = staffService.findById(form);
				temp.put("send_cardNo", stafftemp.getCardNo());
				temp.put("send_name", stafftemp.getName());
				temp.put("send_department", stafftemp.getDepartment());
				temp.put("send_position", stafftemp.getPosition());

				stateJson.add(temp);
				i++;
			}
			System.out.println(stateJson);
			out.println(stateJson);
			out.close();
		} catch (StateException e) {
			out.print("0");
			out.close();
			e.printStackTrace();
		}
	}

}
