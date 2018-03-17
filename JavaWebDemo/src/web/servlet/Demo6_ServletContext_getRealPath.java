package web.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ʹ��ServletContext ��ȡ��Դ·��
 * Servlet implementation class Demo6_ServletContext_getRealPath
 */
@WebServlet("/demo6")
public class Demo6_ServletContext_getRealPath extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo6_ServletContext_getRealPath() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		�õ��������̷���·����F:/xxx/xx/x	��ô�������Դ�أ�URL?
		String path = this.getServletContext().getRealPath("/index.jsp");//��סҪб��
		//E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JavaWebDemo\index.jsp
		//�õ�·���Ϳ�����File����,Ȼ��Ϳ���ɾ������ļ���Ҳ���Ա��������,�Ϳ����������(����)
		System.out.println(path);
		
		InputStream is = this.getServletContext().getResourceAsStream("/index.jsp");//��������2��
//		String path = this.getServletContext().getRealPath("/index.jsp");
//		FileInputStream fis = new FileInputStream(path);
		is.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
