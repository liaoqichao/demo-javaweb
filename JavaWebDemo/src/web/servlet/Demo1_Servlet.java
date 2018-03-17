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
	 * Servlet中大多数方法不是我们调用,而是Tomcat调用。并且的Servlet的对象也是由Tomcat创建
	 * 怎么通过浏览器访问Demo1_Servlet.java?通过配置WebContent/WEB-INF/web.xml
	 */
	/*
	 * destroy是生命周期方法
	 * Servlet被销毁之前Tomcat调用destroy,并且它指挥被调用一次。这个方法不是自杀的方法,而是自杀前留遗言的方法。
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
		//获取Servlet配置信息
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
		//获取Servlet信息
		System.out.println("getServletInfo()..");
		return null;
	}

	/*
	 * 生命周期方法：Servlet对象创建后马上执行,并只执行一次。
	 * init是生命周期方法
	 */
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		this.config = arg0;
		System.out.println("init(ServletConfig arg0)..");
		/*
		 * 获取初始化参数
		 */
		System.out.println("p1:"+arg0.getInitParameter("p1"));
		System.out.println("p2:"+arg0.getInitParameter("p2"));
		
		/*
		 * 获取所有初始化参数的名称
		 */
		Enumeration<String> e = arg0.getInitParameterNames();
		ArrayList<String> al = Collections.list(e);
		for (String string : al) {
			System.out.println(string);
		}
	}
	/*
	 * service是生命周期方法
	 * 它会被调用多次!!每次处理请求都是在调用这个方法
	 */
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service(ServletRequest arg0,ServletResponse arg1)..");
	}

}
