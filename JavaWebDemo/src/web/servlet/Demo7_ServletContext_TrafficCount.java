package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ʹ��ServletContextʵ�ַ�����ͳ��
 * Servlet implementation class Demo7_ServletContext_TrafficCount
 */
@WebServlet("/demo7")
public class Demo7_ServletContext_TrafficCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo7_ServletContext_TrafficCount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//1.��ȡServletContext����
		//2.��ServletContext��ȡcount����
		//3.1 ���û����setAttribute,����ֵΪ1
		//3.2����л�ȡ����ֵ,count++,��setAttribute
		ServletContext application = this.getServletContext();
		Integer count = (Integer)application.getAttribute("count");
		if(count==null){
			application.setAttribute("count", 1);//����д�ַ���1�Ļ�����count++
		}else{
			count++;
			application.setAttribute("count", count);
		}
		/*
		 * ����������
		 * 	��Ҫʹ����Ӧ����response
		 */
		PrintWriter pw = response.getWriter();
		pw.print("<h1>" +application.getAttribute("count")+ "</h1>");//ֱ����count�Ļ���һ����null
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
