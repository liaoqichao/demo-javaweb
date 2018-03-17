package web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Demo1_Servlet implements Servlet {
	/**
	 * uri-pattern = Demo1_Servlet
	 */
	/*
	 * Servlet�д���������������ǵ���,����Tomcat���á����ҵ�Servlet�Ķ���Ҳ����Tomcat����
	 * ��ôͨ�����������Demo1_Servlet.java?ͨ������WebContent/WEB-INF/web.xml
	 */
	/*
	 * destroy���������ڷ���
	 * Servlet������֮ǰTomcat����destroy,������ָ�ӱ�����һ�Ρ��������������ɱ�ķ���,������ɱǰ�����Եķ�����
	 */
	private ServletConfig config;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy()...");
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		//��ȡServlet������Ϣ
//		<servlet>
//			<servlet-name>demo1</servlet-name>
//			<servlet-class>servlet.Demo1_Servlet</servlet-class>
//		</servlet>
		System.out.println("getServletConfig()..");
		return config;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		//��ȡServlet��Ϣ
		System.out.println("getServletInfo()..");
		return null;
	}

	/*
	 * �������ڷ�����Servlet���󴴽�������ִ��,��ִֻ��һ�Ρ�
	 * init���������ڷ���
	 */
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		this.config = arg0;
		System.out.println("init(ServletConfig arg0)..");
		/*
		 * ��ȡ��ʼ������
		 */
		System.out.println("p1:"+arg0.getInitParameter("p1"));
		System.out.println("p2:"+arg0.getInitParameter("p2"));
		
		/*
		 * ��ȡ���г�ʼ������������
		 */
		Enumeration<String> e = arg0.getInitParameterNames();
		ArrayList<String> al = Collections.list(e);
		for (String string : al) {
			System.out.println(string);
		}
	}
	/*
	 * service���������ڷ���
	 * ���ᱻ���ö��!!ÿ�δ����������ڵ����������
	 */
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service(ServletRequest arg0,ServletResponse arg1)..");
	}

}
