package web.servlet;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import sun.misc.BASE64Encoder;

/**
 * 演示下载
 */
@WebServlet("/Download1Servlet")
public class Download1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 两个头一个流。
		 * 1.Content-Type
		 * 2.Content-Disposition
		 * 3.流：下载文件的数据。
		 */
		
		// 从数据库获得文件路径
		String filename = "E:/Eclipse/IO/ByteStream/倉木麻衣-SAFEST PLACE.wma";
		
		String name = filename.substring(filename.lastIndexOf("/")+1);
		name =  filenameEncoding(name,request);
		//1.
		String contentType = this.getServletContext().getMimeType(filename);//通过文件名称获取MINE类型
		//2.
		String contentDisposition = "attachment;filename="
				+ name;//带中文容易乱码
		/*
		 * 解决文件名(Content-Disposition中的filename)的编码问题。就是解决下载框中显示的文件名的乱码问题。
		 * 但是如果名字有"+","_"等个别字符会出现问题。
		 */
//		contentDisposition = new String(contentDisposition.getBytes("GBK"),"ISO-8859-1");
		
		//3.
		FileInputStream fis = new FileInputStream(filename);
		
		//设置头
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Disposition", contentDisposition);
		
		//获取绑定客户端的流
		ServletOutputStream out = response.getOutputStream();
		IOUtils.copy(fis, out);//把输入流的数据写入到输出流中。
		
		fis.close();
		
	}

	/**
	 * 解决attachment的filename出现乱码的问题。
	 * filename = URLEncoder.encode(filename, "utf-8");空格变成加号。
	 * filename = new String(filename.getBytes("GBK"),"ISO-8859-1");空格不会变加号
	 * @param filename
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
		String agent = request.getHeader("User-Agent"); //获取浏览器
		if (agent.contains("Firefox")) {
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filename = "=?utf-8?B?"
					+ base64Encoder.encode(filename.getBytes("utf-8"))
					+ "?=";
		} else if(agent.contains("MSIE")) {
//			filename = URLEncoder.encode(filename, "utf-8");//用这个空格会变加号
			filename = new String(filename.getBytes("GBK"),"ISO-8859-1");
		} else {
//			filename = URLEncoder.encode(filename, "utf-8");
			filename = new String(filename.getBytes("GBK"),"ISO-8859-1");
		}
		return filename;
	}

}


