package web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 演示ServletContextListner监听ServletContext的创建和销毁
 * HttpSessionListener和ServletRequestListener一样
 * 可以在这个监听器中存放Tomcat一启动时就要执行的代码
 * @author Administrator
 *
 */
//有这个就可以不用在web.xml中注册
//@WebListener
public class Demo37_Listener implements ServletContextListener {
//		在web.xml中配置
//	  	<listener>
//	  		<listener-class>web.listener.Demo37_Listener</listener-class>
//	 	 </listener>
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("contextDestroyed,销毁前调用...");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("contextInitialized,出生后调用...");
	}

}
