package oa.document.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import oa.document.domain.Document;
import oa.document.domain.State;
import oa.document.service.DocumentService;
import oa.document.service.StateService;
import oa.draft.service.DraftService;
import oa.staff.domain.Staff;
import sun.misc.BASE64Encoder;

/**
 * Servlet implementation class DownDocument
 */
public class DownDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownDocument() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 演示下载
		String documentNo = request.getParameter("documentNo");// 获取要下载的文件名
		System.out.println(documentNo);
		
		boolean athority=false;
		Staff staff=(Staff)request.getSession().getAttribute("user");
		
		DocumentService documentService = new DocumentService();
		Document document = documentService.findDocumentById(documentNo);
		if (document == null) {
			DraftService draftService = new DraftService();
			document = draftService.findDocumentById(documentNo);
		}
		
		//查找他是否有权限下载，必须要是发送方或者接收方
		//发送方判断：
		if(document.getDraftsmanCardNo().equals(staff.getCardNo())){
			athority=true;
		}
		else{	//判断接收方
			StateService stateService=new StateService();
			State state=stateService.findByHeaderCardNo(document.getDocumentHeader(), staff.getCardNo());
			if(state!=null){
				athority=true;
			}
		}
		if(athority==true){
			File file = document.getDocument();
			System.out.println(file.getAbsolutePath());

			String filename = file.getAbsolutePath();
			String name = filename.substring(filename.lastIndexOf("/") + 1);
			name = filenameEncoding(name, request);
			// 1.
			String contentType = this.getServletContext().getMimeType(filename);// 通过文件名称获取MINE类型
			// 2.
			String contentDisposition = "attachment;filename=" + name;// 带中文容易乱码
			/*
			 * 解决文件名(Content-Disposition中的filename)的编码问题。就是解决下载框中显示的文件名的乱码问题。
			 * 但是如果名字有"+","_"等个别字符会出现问题。
			 */
			// contentDisposition = new
			// String(contentDisposition.getBytes("GBK"),"ISO-8859-1");

			// 3.
			FileInputStream fis = new FileInputStream(filename);

			// 设置头
			response.setHeader("Content-Type", contentType);
			response.setHeader("Content-Disposition", contentDisposition);

			// 获取绑定客户端的流
			ServletOutputStream out = response.getOutputStream();
			IOUtils.copy(fis, out);// 把输入流的数据写入到输出流中。

			fis.close();
		}
		else{
			PrintWriter pw=response.getWriter();
			pw.write("你没有此文档");
			pw.close();
		}
	}

		public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
			String agent = request.getHeader("User-Agent"); // 获取浏览器
			if (agent.contains("Firefox")) {
				BASE64Encoder base64Encoder = new BASE64Encoder();
				filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
			} else if (agent.contains("MSIE")) {
				// filename = URLEncoder.encode(filename, "utf-8");//用这个空格会变加号
				filename = new String(filename.getBytes("GBK"), "ISO-8859-1");
			} else {
				// filename = URLEncoder.encode(filename, "utf-8");
				filename = new String(filename.getBytes("GBK"), "ISO-8859-1");
			}
			return filename;
		}

	}
			
