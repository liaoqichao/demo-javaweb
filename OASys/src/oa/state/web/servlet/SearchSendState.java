package oa.state.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oa.document.domain.Document;
import oa.document.domain.State;
import oa.document.service.StateService;
import oa.staff.domain.Staff;

/**
 * Servlet implementation class SearchState
 */
public class SearchSendState extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchSendState() {
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
		
		Staff staff=(Staff)request.getSession().getAttribute("user");

		PrintWriter out = response.getWriter();
		StateService stateService = new StateService();

		Staff stafftemp = null;
		try {
			List<Map<String,Object>> mapList=stateService.findBySendCardNo(staff.getCardNo());
			JSONArray stateJson = new JSONArray();
			Document document=null;
			
			for(Map<String,Object> map  : mapList) {
				JSONObject temp = new JSONObject();
				//	保存文档信息
				document=(Document) map.get("document");
				temp.put("documentHeader", document.getDocumentHeader());
				temp.put("documentNo", document.getDocumentNo());
				temp.put("draftsmanCardNo", document.getDraftsmanCardNo());
				temp.put("issuerCardNo", document.getIssuerCardNo());
				//保存状态信息
				State stateTemp =  (State) map.get("state");
				temp.put("state", stateTemp.getState());
				temp.put("time", stateTemp.getTime());
				temp.put("stateNo", stateTemp.getStateNo());
				//保存接收人信息	
				stafftemp = (Staff)map.get("receive");
				temp.put("receiver_cardNo", stafftemp.getCardNo());
				temp.put("receiver_name", stafftemp.getName());
				temp.put("receiver_department", stafftemp.getDepartment());
				temp.put("receiver_position", stafftemp.getPosition());
				//保存发送人信息
				stafftemp =  (Staff)map.get("send");
				temp.put("send_cardNo", stafftemp.getCardNo());
				temp.put("send_name", stafftemp.getName());
				temp.put("send_department", stafftemp.getDepartment());
				temp.put("send_position", stafftemp.getPosition());

				stateJson.add(temp);
			}
			
			System.out.println(stateJson);
			out.println(stateJson);
			out.close();
		} catch (Exception e) {
			out.print("0");
			out.close();
			e.printStackTrace();
		}
	}

}
