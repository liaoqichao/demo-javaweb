package demo8.service;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import demo8.service.impl.HelloServiceImpl;

/**
 * 使用JaxWsServerFactoryBean发布WebService
 * @WebService注解方在实现类上
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
		 * 加入消息拦截器。LoggingIn表示请求，LoggingOut表示响应
		 * LoggingInInterceptor表示请求消息拦截器，LoggingOutInterceptor表示响应消息拦截器
		 */
		jwsfb.getInInterceptors().add(new LoggingInInterceptor());
		jwsfb.getOutInterceptors().add(new LoggingOutInterceptor());
		
		jwsfb.create();
		System.out.println("HelloService ready...");
	}
}
