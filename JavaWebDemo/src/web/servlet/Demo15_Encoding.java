package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Demo15_Encoding
 */
@WebServlet({ "/Demo15_Encoding", "/demo15" })
public class Demo15_Encoding extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo15_Encoding() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * ��ȡ�������
		 * 1.�Ȼ�ȡ�����ַ���
		 * 2.����ISO����
		 * 3.��UTF-8����
		 * 
		 * ���������Ӧ
		 * 1.��getWriter()֮ǰresponse.setContentType("text/html;charset=utf-8");
		 */
		String username = request.getParameter("user");
//		byte[] bytes = username.getBytes("iso-8859-1");
//		username = new String(bytes, "utf-8");
//		����Ҫ��ԭ��Ĭ�Ͼ���UTF-8������ISO-8859-1. ΪʲôSERVER.XML�Ҳ����������?
		System.out.println(username);
		
		response.setContentType("text/html;charset=utf-8");	//û������������
		response.getWriter().print("��Ӧ...");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * ��ȡ�������
		 * 1.�ڻ�ȡ����֮ǰ�ȵ���request.setCharacterEncoding("utf-8");
		 * 2.ʹ��getParameter()��ȡ����
		 * 
		 * ���������Ӧ
		 * 1.��getWriter()֮ǰresponse.setContentType("text/html;charset=utf-8");
		 */
		request.setCharacterEncoding("utf-8");//û�����6���ʺš�??????
		String username = request.getParameter("user");
		System.out.println(username);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("��Ӧ...");
	}

}
