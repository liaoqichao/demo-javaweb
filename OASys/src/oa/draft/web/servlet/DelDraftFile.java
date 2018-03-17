package oa.draft.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.document.domain.Document;
import oa.document.domain.State;
import oa.document.service.DocumentService;
import oa.document.service.StateService;
import oa.draft.service.DraftService;
import oa.staff.domain.Staff;

/**
 * Servlet implementation class DelDocument
 */
public class DelDraftFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DraftService draftService = new DraftService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DelDraftFile() {
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

		PrintWriter out = response.getWriter();
		String documentNo = request.getParameter("documentNo");
		boolean athority=false;

		// 获取数据库中对象
		Document document = draftService.findDocumentById(documentNo);
		//获取操作人信息
		Staff staff=(Staff)request.getSession().getAttribute("user");
		if(staff.getCardNo().equals(document.getDraftsmanCardNo())){
			athority=true;
		}
		if(staff.getCardNo().equals(document.getIssuerCardNo())){
			athority=true;
		}
		
		//判断权限， 删除数据库中的对象
		try {
			if(athority==false){
				out.print(0);
				out.close();
			}
			else{
				Document temp=draftService.findByDocumentNo(documentNo);
				if(temp==null){
					DocumentService documentService=new DocumentService();
					temp=documentService.findByDocumentNo(documentNo);
				}
				StateService stateService=new StateService();
				List<State> stateList=stateService.findByDocumentHeader(temp.getDocumentHeader());
				int i=0;
				while(i<stateList.size()){
					stateService.delete(stateList.get(i));
					i++;
				}
				draftService.cancel(document);
				out.print(1);
				out.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			out.print(0);
			out.close();
		}
	}
}
