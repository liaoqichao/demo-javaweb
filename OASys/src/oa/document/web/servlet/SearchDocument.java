package oa.document.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oa.document.domain.Document;
import oa.document.domain.State;
import oa.document.service.DocumentService;
import oa.document.service.StateException;
import oa.document.service.StateService;
import oa.draft.service.DraftService;
import oa.staff.domain.Staff;
import oa.staff.service.StaffService;

/**
 * Servlet implementation class SearchDocument
 */
public class SearchDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchDocument() {
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

		List<State> stateList =new ArrayList<State>();

		Staff staff=(Staff) request.getSession().getAttribute("user");

		StateService stateService = new StateService();
		if (documentHeader.equals("")) {
			if (state.equals("0")) {
				List<State>B = stateService.findByState(state, staff.getCardNo());
				List<State> A = stateService.findByState("5", staff.getCardNo());
				stateList.addAll(B);
				stateList.addAll(A);
			} else if (state.equals("5")) {
				stateList = stateService.findByState("5", staff.getCardNo());
			} else {
				stateList =stateService.findByStateWithout05(staff.getCardNo());
			}

		} else {
			try {
				stateList = stateService.findByDocumentHeader(staff.getCardNo(), documentHeader);
			} catch (StateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int i = 0;
		State stateTemp = null;
		DocumentService df = new DocumentService();
		DraftService draftService = new DraftService();
		StaffService staffService=new StaffService();
		JSONArray documentJson = new JSONArray();
		Document document=null;
		Staff form=new Staff();
		Staff tempform=null;
		
		while (i < stateList.size()) {
			stateTemp = stateList.get(i);
			JSONObject temp = new JSONObject();

			temp.put("documentHeader", stateTemp.getDocumentHeader());
			
			document=df.findByDocumentHeader(stateTemp.getDocumentHeader());// 查询Documenttable表得到Document
			
			if (document==null) {		// 查询filetable表得到DocumentNo
				document=draftService.findByDocumentHeader(stateTemp.getDocumentHeader());
			}

			temp.put("documentNo", document.getDocumentNo());
			temp.put("draftsmanCardNo", document.getDraftsmanCardNo());
			
			form.setCardNo(document.getDraftsmanCardNo());
			tempform=staffService.findById(form);	
			temp.put("draftsman_name", tempform.getName());
			temp.put("draftsman_department", tempform.getDepartment());
			temp.put("draftsman_position", tempform.getPosition());
			
			//起草人为空，要判断
			if(document.getIssuerCardNo().equals("")){
				temp.put("issue_name", "-1");
				temp.put("issue_department", "");
				temp.put("issue_position", "");
			}else{
				temp.put("issuerCardNo", document.getIssuerCardNo());
				form.setCardNo(document.getIssuerCardNo());
				tempform=staffService.findById(form);		
				temp.put("issue_name", tempform.getName());
				temp.put("issue_department", tempform.getDepartment());
				temp.put("issue_position", tempform.getPosition());
			}
			
			temp.put("state", stateTemp.getState());
			temp.put("time", stateTemp.getTime());
			temp.put("stateNo", stateTemp.getStateNo());
			documentJson.add(temp);
			i++;
		}

		System.out.println(documentJson);
		PrintWriter out = response.getWriter();
		out.print(documentJson);
		out.close();
	}
}
