package oa.draft.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import oa.document.service.StateService;
import oa.draft.service.DraftService;
import oa.staff.domain.Staff;

/**
 * Servlet implementation class SearchDocument
 */
public class SearchDraft extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchDraft() {
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
		DraftService draftService = new DraftService();
		DocumentService documentService = new DocumentService();
		StateService stateService = new StateService();
		State state = null;

		Staff staff=(Staff)request.getSession().getAttribute("user");
		//保存未通过起草的文件信息
		List<Document> documentList = draftService.findByHeader(documentHeader, staff.getCardNo());
		JSONArray documentJson = new JSONArray();
		for (Document document : documentList) { // 这里将document实体里的，document文件删除了
			JSONObject temp = new JSONObject();
			temp.put("documentNo", document.getDocumentNo());
			temp.put("documentHeader", document.getDocumentHeader());
			temp.put("draftsmanCardNo", document.getDraftsmanCardNo());
			temp.put("issuerCardNo", document.getIssuerCardNo());
			temp.put("suffix", document.getSuffix());
			state = stateService.findByHeaderCardNo(document.getDocumentHeader(), document.getIssuerCardNo());
			if (state == null) {//如果未设置签发人，默认为需签发
				temp.put("state", "5");
			} else {
				temp.put("state", state.getState());
				System.out.println(state.getState());
			}
			documentJson.add(temp);
		}
		//保存通过起草的文件信息
		List<Document> A = documentService.findByHeader(documentHeader, staff.getCardNo());
		for (Document document : A) { // 这里将document实体里的，document文件删除了
			JSONObject temp = new JSONObject();
			temp.put("documentNo", document.getDocumentNo());
			temp.put("documentHeader", document.getDocumentHeader());
			temp.put("draftsmanCardNo", document.getDraftsmanCardNo());
			temp.put("issuerCardNo", document.getIssuerCardNo());
			temp.put("suffix", document.getSuffix());
			state = stateService.findByHeaderCardNo(document.getDocumentHeader(), document.getIssuerCardNo());
			if (state == null) {//如果未设置签发人，默认为需签发
				temp.put("state", "5");
			} else {
				temp.put("state", state.getState());
				System.out.println(state.getState());
			}
			documentJson.add(temp);
		}

		System.out.println(documentJson);
		PrintWriter out = response.getWriter();
		out.print(documentJson);
		out.close();
			
		
	}
		
}
