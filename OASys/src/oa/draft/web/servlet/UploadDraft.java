package oa.draft.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ComonUtils.CommonUtils;
import oa.document.domain.Document;
import oa.draft.service.DraftService;
import oa.staff.domain.Staff;

/**
 * Servlet implementation class UploadDocument
 */
public class UploadDraft extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadDraft() {
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
	/**
	 * --上传不能使用baseServlet，因为encType="multipart/form-data" 上传的Servlet,
	 * 设置上传文件大小限制，设置上传整个表单的大小限制。 设置缓存大小和临时目录。 处理上传问题： 1.目录打散 2.文件名的绝对路径 3.文件同名
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg = "<script type='text/javascript'>alert('上传失败了');</script>";

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 含参构造器，第一个参数为缓存大小(单位字节,默认10K)，第二个参数为临时目录文件(超出缓存就把文件写到临时目录)
		// DiskFileItemFactory factory = new DiskFileItemFactory(20*1024,new
		// File("E:/Eclipse/temp"));

		// 2.创建解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);

		// 含参构造器，第一个参数为缓存大小(单位字节,默认10K)，第二个参数为临时目录文件(超出缓存就把文件写到临时目录)
		// DiskFileItemFactory factory = new DiskFileItemFactory(20*1024,new
		// File("E:/Eclipse/temp"));
		// 2.创建解析器
		sfu.setFileSizeMax(10*1024 * 1024);
		sfu.setSizeMax(10 * 1024 * 1024);

		// 3.通过解析器得到List<FileItem>
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);

			// FileItem file1 = fileItemList.get(0); // 普通表单项
			// System.out.println(file1.getFieldName() + " : " +
			// file1.getString("UTF-8"));// 普通表单项名称：值

			FileItem file2 = fileItemList.get(0);// 文件表单项
			System.out.println("ContenType: " + file2.getContentType() + " ;size: " + file2.getSize() + " name: "
					+ file2.getName());
			String filename1 = file2.getName();
			filename1 = new String(filename1.getBytes("GBK"),"UTF-8");
					// 4.保存文件
					// File file = new
					// File("E:/Eclipse/IO/JavaWebDemo48-upload/1.jpg");
					// fi2.write(file);

			// 目录打散：
			// 1.得到根路径/WEB-INF/files
			String root = this.getServletContext().getRealPath("/WEB-INF/files/");
			/*
			 * 2.生层两层目录， a.得到文件名称 b.得到哈希值 c.转换成16进制 d.获取前2个字母用来生成目录
			 */
			// a.
			String filename = filename1;
			// 处理文件名的绝对路径问题。
			int index = filename.lastIndexOf("\\");
			if (index != -1) {
				filename = filename.substring(index + 1);
			}
			// 处理文件同名问题
			String uuid = CommonUtils.uuid();
			String savename = uuid + "_" + filename;

			int hashCode = savename.hashCode();
			String hex = Integer.toHexString(hashCode);
			// 有则不创建，没有则创建。/A/B这里先创建A目录
			File dir = new File(root, "" + hex.charAt(0));
			dir.mkdir();
			// 有则不创建，没有则创建。/A/B这里先创建B目录
			root = root + hex.charAt(0);
			dir = new File(root, "" + hex.charAt(1));
			dir.mkdir();
			System.out.println(root + " " + hex.charAt(1));

			// 3.创建目标文件
			File destFile = new File(dir, savename);
			// 4.写并保存文件
			file2.write(destFile);
			// E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JavaWebDemo48\WEB-INF\files

			DraftService draftService = new DraftService();
			Document document = new Document();
			document.setDocumentNo(uuid);
			document.setDocument(destFile);
			document.setDocumentHeader(filename);
			
			Staff staff = (Staff) request.getSession().getAttribute("user");

			document.setDraftsmanCardNo(staff.getCardNo());
			document.setIssuerCardNo("");

			System.out.println(document);
			draftService.draft(document);

			msg = "<script type='text/javascript'>alert('上传成功了');</script>";
			request.setAttribute("message", msg);
			this.getServletConfig().getServletContext().getRequestDispatcher("/draft/draft.jsp").forward(request,
					response);
		} catch (Exception e) {
			if (e instanceof FileUploadBase.FileSizeLimitExceededException) {
				msg = "<script type='text/javascript'>alert('您上传的文件超出10M');</script>";
				request.setAttribute("message", msg);
				this.getServletConfig().getServletContext().getRequestDispatcher("/draft/draft.jsp").forward(request,
						response);
			} else if (e instanceof FileUploadBase.SizeLimitExceededException) {
				msg = "<script type='text/javascript'>alert('您上传的表单超出10M');</script>";
				request.setAttribute("message", msg);
				this.getServletConfig().getServletContext().getRequestDispatcher("/draft/draft.jsp").forward(request,
						response);
			} else {
				request.setAttribute("message", msg);
				this.getServletConfig().getServletContext().getRequestDispatcher("/draft/draft.jsp").forward(request,
						response);
				throw new RuntimeException(e);
			}
		}
	}
}
