package oa.document.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ComonUtils.CommonUtils;
import oa.document.domain.Document;
import oa.document.domain.State;
import oa.document.service.DocumentService;
import oa.document.service.StateService;
import oa.draft.service.DraftService;
import oa.staff.domain.Staff;

/**
 * Servlet implementation class SetSendState
 */
public class SetSendState extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StateService stateService = new StateService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetSendState() {
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
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String cardNo = request.getParameter("addStr").toString();
		String documentNo = request.getParameter("documentNo").toString();
		String saveState = request.getParameter("state").toString();
		String no[] = cardNo.split(",");

		PrintWriter out = response.getWriter();
		DocumentService ds = new DocumentService();
		Document document=ds.findByDocumentNo(documentNo);
		if(document==null){
			DraftService draftService=new DraftService();
			document=draftService.findByDocumentNo(documentNo);
		}
		String header =document.getDocumentHeader();
		Staff staff=(Staff)request.getSession().getAttribute("user");

		State state = new State();
		java.util.Date date = new java.util.Date();
		java.sql.Date dataTemp = new java.sql.Date(date.getTime());

		int i = 0;
		try {
			while (i < no.length) {
				state.setCardNo(no[i]);
				state.setDocumentHeader(header);
				state.setState(saveState);
				state.setTime(dataTemp);
				state.setSendCardNo(staff.getCardNo());

				if (stateService.findByHeaderCardNo(header, no[i]) == null) {
					String uuid = CommonUtils.uuid();
					state.setStateNo(uuid);
					stateService.save(state);
				} else {
					stateService.update(state);
				}
				i++;
			}
			out.print("1");
			out.close();
		} catch (Exception e) {
			out.print("0");
			out.close();
			e.printStackTrace();
		}

	}

}
