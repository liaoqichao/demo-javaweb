package oa.issue.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ComonUtils.CommonUtils;
import oa.document.domain.Document;
import oa.document.domain.ModifyDocument;
import oa.document.domain.State;
import oa.document.service.DocumentService;
import oa.document.service.ModifyDocumentService;
import oa.document.service.StateService;
import oa.draft.service.DraftService;
import oa.message.domain.Message;
import oa.message.service.MessageService;
import oa.staff.domain.Staff;
import oa.staff.service.StaffService;

/**
 * Servlet implementation class SearchDocument
 */
public class Issue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Issue() {
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
		
		//获得文件编号，文件名，状态，备注
		String documentNo = request.getParameter("documentNo").toString();
		String documentHeader = request.getParameter("Header").toString();
		String stateflag = request.getParameter("state").toString();
		String remarks = request.getParameter("remarks").toString();
		System.out.println(documentHeader + " " + documentNo + "  " + remarks);

		Staff staff=(Staff) request.getSession().getAttribute("user");
		Document document =null;
		PrintWriter out = response.getWriter();
		try {
		
			if (stateflag.equals("6")) {		//状态为6时，表示签发。同时保存状态，把文件从filetable迁移到documenttable
				DraftService draftService = new DraftService();		
				document = draftService.findDocumentById(documentNo);	//文件从filetable得到。实体类名为：document
				if(document==null){
					System.out.println("kong");
				}
				
				DocumentService documentService = new DocumentService();
				documentService.save(document);			//保存document到documenttable
				draftService.cancel(document);					//filetable删除原文件
			}
			else{
				DraftService draftService = new DraftService();		
				document = draftService.findDocumentById(documentNo);	//文件从filetable得到。实体类名为：document
			}
			
			//下面是改变状态
			StateService stateService = new StateService();
			State state = new State();
			java.util.Date date = new java.util.Date();
			java.sql.Date dataTemp = new java.sql.Date(date.getTime());

			// 改变状态
			state.setCardNo(staff.getCardNo());
			state.setDocumentHeader(documentHeader);
			state.setState(stateflag); // 通过
			state.setTime(dataTemp);
			if (stateService.findByHeaderCardNo(documentHeader, staff.getCardNo()) == null) {
				String uuid = CommonUtils.uuid();
				state.setStateNo(uuid);
				stateService.save(state);
			} else {
				stateService.update(state);
			}

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
			//向起草人发送一个信息
			MessageService messageService=new MessageService();
			Message message=new Message();
			String content="";
			//消息接收人、文件起草人
			Staff form=new Staff();
			form.setCardNo(document.getDraftsmanCardNo());
			StaffService staffService=new StaffService();
			Staff receiver=staffService.findById(form);
			message.setReceiveCardNo(receiver.getCardNo());
			message.setReceiveName(receiver.getName());
			//消息发送人、文件签发人
			message.setSendCardNo(staff.getCardNo());
			message.setSendName(staff.getName());
			//消息内容
			content="您起草的"+document.getDocumentHeader()+","+staff.getDepartment()+"的"+staff.getName()+"先生";
			if(stateflag.equals("6")){
				content=content+"已签发";
			}
			else{
				content=content+"已拒绝签发，您可以从备注中查找到原因";
			}
			message.setMessage(content);
			
			String uuid = CommonUtils.uuid();
			message.setMessageNo(uuid);
			message.setState(0);
//			java.sql.Date dataTemp = new java.sql.Date(date.getTime());
			message.setTime(date);
			
			messageService.save(message);
	
			out.print("1");
			out.close();
		} catch (Exception e) {
			out.print("0");
			out.close();
			e.printStackTrace();
		}
	}
}
