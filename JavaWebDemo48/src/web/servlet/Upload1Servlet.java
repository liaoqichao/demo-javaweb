package web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import lqcUtils.CommonUtils;

/**
 * --上传不能使用baseServlet，因为encType="multipart/form-data"
 * 上传的Servlet,
 * 设置上传文件大小限制，设置上传整个表单的大小限制。
 * 设置缓存大小和临时目录。
 * 处理上传问题：
 * 1.目录打散
 * 2.文件名的绝对路径
 * 3.文件同名
 */
@WebServlet("/Upload1Servlet")
public class Upload1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.处理编码问题,这个没有用。因为是enctype="multipary/form-data"
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		//1.创建DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//含参构造器，第一个参数为缓存大小(单位字节,默认10K)，第二个参数为临时目录文件(超出缓存就把文件写到临时目录)
//		DiskFileItemFactory factory = new DiskFileItemFactory(20*1024,new File("E:/Eclipse/temp"));
		//2.创建解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		
		/*
		 * 这2句话必须在sfu.parseRequest(request);之前调用。如果上传的文件超出限制，在parseRequest执行时会抛出异常。
		 */
		sfu.setFileSizeMax(1024 * 1024);//设置上传单个文件大小的限制为1M
		sfu.setSizeMax(10 * 1024 *1024);//设置整个表单数据的大小为10M
		//3.通过解析器得到List<FileItem>
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);
			FileItem fi1 = fileItemList.get(0);//普通表单项
			System.out.println(fi1.getFieldName()+" : "+fi1.getString("UTF-8"));//普通表单项名称：值
			
			FileItem fi2 = fileItemList.get(1);//文件表单项
			System.out.println("ContentType: " + fi2.getContentType()
			+ ", size: "+fi2.getSize()+" ,filename: "+fi2.getName());
			//4.保存文件
//			File file = new File("E:/Eclipse/IO/JavaWebDemo48-upload/1.jpg");
//			fi2.write(file);
			
			//目录打散：
			//1.得到根路径/WEB-INF/files
			String root = this.getServletContext().getRealPath("/WEB-INF/files/");

			/*
			 * 2.生层两层目录，
			 * a.得到文件名称
			 * b.得到哈希值
			 * c.转换成16进制
			 * d.获取前2个字母用来生成目录
			 */
			//a.
			String filename = fi2.getName();
			//处理文件名的绝对路径问题。
			int index = filename.lastIndexOf("\\");
			if(index != -1){
				filename  = filename.substring(index+1);
			}
			//处理文件同名问题
			String savename = CommonUtils.uuid() + "_" + filename; 
			//b.
			int hashCode = savename.hashCode();
			//c.
			String hex = Integer.toHexString(hashCode);
			//d.
			File dir = new File(root,hex.charAt(0)+"/"+hex.charAt(1));
			dir.mkdirs();//有则不创建，没有则创建。/A/B 如果没有/A也会创建/A/B。 mkdir的话如果没有/A就不会创建/A/B
			
			//3.创建目标文件
			File destFile = new File(dir,savename);
			//4.写并保存文件
			fi2.write(destFile);
			//E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JavaWebDemo48\WEB-INF\files
		} catch (Exception e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException){
				request.setAttribute("msg", "您上传的文件超出1M");
				request.getRequestDispatcher("/form1.jsp").forward(request, response);
			} else if( e instanceof FileUploadBase.SizeLimitExceededException){
				request.setAttribute("msg", "您上传的表单超出10M");
				request.getRequestDispatcher("/form1,jsp").forward(request, response);
			} else{
				throw new RuntimeException(e);
			}
		}
	}

}
