package web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ��ʾServletContextListner����ServletContext�Ĵ���������
 * HttpSessionListener��ServletRequestListenerһ��
 * ����������������д��Tomcatһ����ʱ��Ҫִ�еĴ���
 * @author Administrator
 *
 */
//������Ϳ��Բ�����web.xml��ע��
//@WebListener
public class Demo37_Listener implements ServletContextListener {
//		��web.xml������
//	  	<listener>
//	  		<listener-class>web.listener.Demo37_Listener</listener-class>
//	 	 </listener>
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("contextDestroyed,����ǰ����...");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("contextInitialized,���������...");
	}

}
