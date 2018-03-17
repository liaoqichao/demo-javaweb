

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * servlet3.0新特性之上传。
 * 之前的上传request.getParameter(..);不能使用，现在可以了
 */
@WebServlet(urlPatterns = "/BServlet")
@MultipartConfig//有这个注解才支持上传组件
public class BServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 1.getParameter可以使用了
		 */
		String username = request.getParameter("username");	//可以使用了！
		/*
		 * 2.获取文件表单字段对应的part对象
		 */
		Part part = request.getPart("resume");//resume是文件表单字段的name，就是和getParameter一样
		/*
		 * 3.从part中获取想要的数据
		 */
		String mime = part.getContentType();//获取MIME类型
		long size = part.getSize();//获取上传文件的大小
		String name = part.getName();//获取文件字段的名称，就是resume
		//获取这个头的值，这个头包含了上传文件的名称
		String contentDispositionHeader = part.getHeader("Content-Disposition");
		System.out.println(mime);//image/jpeg
//		System.out.println(size);/29414
		System.out.println(name);//resume
		System.out.println(contentDispositionHeader);//form-data; name="resume"; filename="demo2.jpg"
		System.out.println(getFilename(part));
		//保存文件
		part.write("E:/Eclipse/IO/JavaWebDemo52-servlet3.0-upload/JavaWebDemo52-2.jpg");
	}
	
	/**
	 * 截取文件名称
	 * @param Part 文件表单字段
	 * @return Content-Disposition体中的filename对应的值，即文件名称
	 */
	private String getFilename(Part part){
		String contentDispositionHeader = part.getHeader("Content-Disposition");
		int start = contentDispositionHeader.lastIndexOf("filename=\"")+10;//lastIndexOf返回的是f的位置
		int end = contentDispositionHeader.length()-1;
		String filename = contentDispositionHeader.substring(start, end);
		return filename;
	}

}
