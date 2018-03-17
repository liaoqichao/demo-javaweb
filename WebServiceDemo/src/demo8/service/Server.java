package demo8.service;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import demo8.service.impl.HelloServiceImpl;

/**
 * ʹ��JaxWsServerFactoryBean����WebService
 * @WebServiceע�ⷽ��ʵ������
 * @author Liaoqc
 *
 */
public class Server {

	public static void main(String[] args) {
		JaxWsServerFactoryBean jwsfb = new JaxWsServerFactoryBean();
		jwsfb.setAddress("http://127.0.0.1:7788/hello");
		jwsfb.setServiceClass(HelloService.class);
		jwsfb.setServiceBean(new HelloServiceImpl());
		
		/*
		 * ������Ϣ��������LoggingIn��ʾ����LoggingOut��ʾ��Ӧ
		 * LoggingInInterceptor��ʾ������Ϣ��������LoggingOutInterceptor��ʾ��Ӧ��Ϣ������
		 */
		jwsfb.getInInterceptors().add(new LoggingInInterceptor());
		jwsfb.getOutInterceptors().add(new LoggingOutInterceptor());
		
		jwsfb.create();
		System.out.println("HelloService ready...");
	}
}
