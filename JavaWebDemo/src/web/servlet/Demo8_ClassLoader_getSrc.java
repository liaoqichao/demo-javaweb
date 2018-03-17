package web.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ͨ��ClassLoader��ȡ��·������Դ����ȡsrc(/WEB-INF/classes)�µ��ļ������ݡ�
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
		//1.�õ�ClassLoader
//			�ȵõ�Class,�ڵõ�ClassLoader
		ClassLoader c1 = this.getClass().getClassLoader();
		//2.����getResourceAsStream()���õ�inputstream
		InputStream is = c1.getResourceAsStream("servlet/demo8.txt");//���classes·��
		//��src/servlet��.Ϊʲô������.javaֻ������txt?
//		InputStream is = c1.getResourceAsStream("demo8.txt");//��src��ǰĿ¼��
		
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = is.read(buf))!= -1){
			System.out.println(new String(buf,0,len));
		}
//		System.out.println(str);
		is.close();
		
//		--------------------------------------------------------
		/*
		 * ʹ��Class c = this.getClass();
		 * c.getResourceAsStream("a.txt");this.getClass()�ĵ�ǰĿ¼
		 * c.getResourceAsStream("/a.txt");classesǰ��Ŀ¼
		 */
		//1,2
//		InputStream is1 = this.getClass().getResourceAsStream("demo8.txt");
//		//3.IO���Ĳ���
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
