package web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��ȡ����ip��User-Agent����ͷ��
 * ��ȡ�ͻ��˵�ip,����ʽ,����User-Agent����ͷ��ȡ����ϵͳ���������Ϣ
 * Servlet implementation class Demo11_HttpServletRequest_getUserAgent
 * 
 * Referer����ͷ��������
 */
@WebServlet("/demo11")
public class Demo11_HttpServletRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo11_HttpServletRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
		 * ��ȡ����ip,
		 * ��ȡUser-Agent��Ϣ
		 */
//		String ip = request.getRemoteAddr();
//		System.out.println("ip : "+ip);						//��ȡip��ַ
//		System.out.println("����ʽ:"+request.getMethod());	//��ȡ����ʽ,ֻ�б���method="post"����POST��ʽ
//		String userAgent = request.getHeader("User-Agent");	//��ȡ��ΪUser-Agent����ͷ����Ϣ
//		System.out.println(userAgent);
//		if(userAgent.toLowerCase().contains("msie")){
//			System.out.println("���õ���IE������ں˵������");
//		}else if(userAgent.toLowerCase().contains("chrome")){
//			System.out.println("���õ��ǹȸ�������ں˵������");
//		}else if(userAgent.toLowerCase().contains("firefox")){
//			System.out.println("���õ��ǻ��������ں˵������");
//		}
		
		/**
		 * Referer��������
		 * ��http://localhost:8080/JavaWebDemo/demo11.html����url������localhost����,�����ɹ�
		 * ���ֱ���ڵ�ַ������http://localhost:8080/JavaWebDemo/demo11��������ҳ
		 */
		String referer = request.getHeader("Referer");//���ֱ�Ӵӵ�ַ�������ַ,��ֵΪnull	
		System.out.println(referer);//http://localhost:8080/JavaWebDemo/demo11.html
		if(referer == null || !referer.contains("localhost")){
			response.sendRedirect("/JavaWebDemo/index.jsp");//�ض���
		}else if(referer.contains("localhost")){
			System.out.println("hello,from localhost!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
