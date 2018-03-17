package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��ʾ����ת�����������
 * Servlet implementation class Demo14_HttpServletRequest_RequestDispatcher_a
 */
@WebServlet({ "/Demo14_HttpServletRequest_RequestDispatcher_a", "/demo14_a" })
public class Demo14_HttpServletRequest_RequestDispatcher_a extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo14_HttpServletRequest_RequestDispatcher_a() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("AServlet");
		/**
		 * AServlet������Ӧͷ����Ӧ��A
		 * AServlet����ת����BServlet
		 * BServlet������Ӧ��B
		 * �����AServlet�з�����Ӧͷ,����������Ӧ��B(�������쳣),��������ĵ�ַ������demo14_a
		 * �׳��쳣�������AServlet������̫��,������ô���»�����ת����(�ڲ��л������)
		 * 	����:for(int i=0 ; i < 1024 *24 +1 ;i++){
		 * 			response.getWriter().print("a");
		 * 		}
		 * 	//ҳ������ܶ�a,Ȼ�����̨�׳��쳣
		 * 
		 * A����B,B����C,...,��д��Ӧ���ֻ������һ��Servlet
		 */
		//1.������Ӧͷ����Ӧ��
		response.setHeader("AServletSetHeader", "A");	//������Ӧͷ
		response.getWriter().print("hello AServlet");		//������Ӧ��
		
		/**
		 * ��request�����������,�������ת��֮ǰ
		 */
		request.setAttribute("user", "zhangsan");
		
		//2.����ת�����������
		//����ת�������ڵ���BServlet��sevice()����
		request.getRequestDispatcher("/demo14_b").forward(request, response);
		//�������ʾhello AServlet
		
		//�������
//		request.getRequestDispatcher("/demo14_b").include(request, response);
		//�������ʾ��hello AServlethello BServlet
		
	}

}
