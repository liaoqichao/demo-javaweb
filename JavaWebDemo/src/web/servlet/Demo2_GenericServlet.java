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
		System.out.println("哈哈笑");
		//GenericServlet有2个init方法,一个有参数,一个没参数。有参数的是Tomcat调用的方法。
//		没参数的是为了让子类添加内容同时不覆盖掉Tomcat调用的那个带参数init方法
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("遗言...");
	}
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Demo2_GenericServlet hello world!");
	}

}
