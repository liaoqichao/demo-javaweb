package web.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通过ClassLoader获取类路径下资源。读取src(/WEB-INF/classes)下的文件的内容。
 * Servlet implementation class Demo8_ClassLoader_getSrc
 */
@WebServlet("/demo8")
public class Demo8_ClassLoader_getSrc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo8_ClassLoader_getSrc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//1.得到ClassLoader
//			先得到Class,在得到ClassLoader
		ClassLoader c1 = this.getClass().getClassLoader();
		//2.调用getResourceAsStream()，得到inputstream
		InputStream is = c1.getResourceAsStream("servlet/demo8.txt");//相对classes路径
		//在src/servlet下.为什么读不了.java只读的了txt?
//		InputStream is = c1.getResourceAsStream("demo8.txt");//在src当前目录下
		
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = is.read(buf))!= -1){
			System.out.println(new String(buf,0,len));
		}
//		System.out.println(str);
		is.close();
		
//		--------------------------------------------------------
		/*
		 * 使用Class c = this.getClass();
		 * c.getResourceAsStream("a.txt");this.getClass()的当前目录
		 * c.getResourceAsStream("/a.txt");classes前面目录
		 */
		//1,2
//		InputStream is1 = this.getClass().getResourceAsStream("demo8.txt");
//		//3.IO流的操作
//		byte[] buf = new byte[1024];
//		int len = 0;
//		while((len = is1.read(buf))!= -1){
//			System.out.println(new String(buf,0,len));
//		}
//		is1.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
