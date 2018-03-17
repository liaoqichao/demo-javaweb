package oa.document.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.document.domain.Document;
import oa.document.domain.ModifyDocument;
import oa.document.service.DocumentService;
import oa.document.service.ModifyDocumentService;
import oa.draft.service.DraftService;

/**
 * Servlet implementation class SaveRemarks
 */
public class SearchRemarks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchRemarks() {
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

		String documentNo = request.getParameter("documentNo").toString();
		System.out.println(documentNo);

		PrintWriter out = response.getWriter();
		try {
			DocumentService documentService=new DocumentService();
			Document document=documentService.findByDocumentNo(documentNo);
			
			if(document==null){
				DraftService draftService=new DraftService();
				document=draftService.findByDocumentNo(documentNo);
			}
			
			ModifyDocumentService mdService = new ModifyDocumentService();
			ModifyDocument modifydocument=mdService.findByDocumentCardNo(document.getIssuerCardNo(), documentNo);

			System.out.println(modifydocument);
			out.print(modifydocument.getRemarks());
			out.close();
		} catch (Exception e) {
			out.print("");
			out.close();
			e.printStackTrace();
		}
	}
}
