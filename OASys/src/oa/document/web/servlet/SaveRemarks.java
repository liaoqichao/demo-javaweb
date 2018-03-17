package oa.document.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ComonUtils.CommonUtils;
import oa.document.domain.ModifyDocument;
import oa.document.service.ModifyDocumentService;
import oa.staff.domain.Staff;

/**
 * Servlet implementation class SaveRemarks
 */
public class SaveRemarks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveRemarks() {
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
		String documentHeader = request.getParameter("Header").toString();
		String remarks = request.getParameter("remarks").toString();
		System.out.println(documentHeader + " " + documentNo + "  " + remarks);

		Staff staff=(Staff) request.getSession().getAttribute("user");

		PrintWriter out = response.getWriter();
		try {
			java.util.Date date = new java.util.Date();
			java.sql.Date dataTemp = new java.sql.Date(date.getTime());

			ModifyDocumentService mdService = new ModifyDocumentService();
			ModifyDocument md = new ModifyDocument();
			md.setDocumentHeader(documentHeader);
			md.setDocumentNo(documentNo);
			md.setModifyerCardNo(staff.getCardNo());
			md.setModifyTime(dataTemp);
			md.setRemarks(remarks);
			if (mdService.findByDocumentCardNo(staff.getCardNo(), documentNo) == null) {
				String mduuid = CommonUtils.uuid();
				md.setModifyDocumentRecordNo(mduuid);
				mdService.save(md);
			} else {
				mdService.update(md);
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
