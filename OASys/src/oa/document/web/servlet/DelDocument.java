package oa.document.web.servlet;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oa.document.domain.Document;
import oa.document.domain.State;
import oa.document.service.DocumentService;
import oa.document.service.StateService;
import oa.draft.service.DraftService;

/**
 * Servlet implementation class DelDocument
 */
public class DelDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DocumentService documentService = new DocumentService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DelDocument() {
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

		// 获取数据库中对象
		Document document = new Document();
		document.setDocumentNo(documentNo);

		// 删除数据库中的对象
		try {
			Document temp=documentService.findByDocumentNo(documentNo);
			if(temp==null){
				DraftService draftService=new DraftService();
				temp=draftService.findByDocumentNo(documentNo);
			}
			StateService stateService=new StateService();
			List<State> stateList=stateService.findByDocumentHeader(temp.getDocumentHeader());
			int i=0;
			while(i<stateList.size()){
				stateService.delete(stateList.get(i));
				i++;
			}
			documentService.cancel(document);
			
			out.print(1);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			out.print(0);
			out.close();
		}
	}
}
