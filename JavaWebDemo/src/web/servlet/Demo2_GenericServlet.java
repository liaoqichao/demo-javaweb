package web.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Demo2_GenericServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 197731593361243952L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("����Ц");
		//GenericServlet��2��init����,һ���в���,һ��û�������в�������Tomcat���õķ�����
//		û��������Ϊ���������������ͬʱ�����ǵ�Tomcat���õ��Ǹ�������init����
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("����...");
	}
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Demo2_GenericServlet hello world!");
	}

}
