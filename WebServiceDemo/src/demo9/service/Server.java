package demo9.service;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import demo9.service.impl.HelloServiceImpl;

/**
 * ʹ��SOAP1.2Э�鷢��WebService
 * �ڷ���ӿڼ���@BindingType(value=SOAPBinding.SOAP12HTTP_BINDING)
 * @author Liaoqc
 *
 */
public class Server {
	public static void main(String[] args) {
		JaxWsServerFactoryBean jwsfb = new JaxWsServerFactoryBean();
		jwsfb.setAddress("http://127.0.0.1:6789/hello");
		jwsfb.setServiceClass(HelloService.class);
		jwsfb.setServiceBean(new HelloServiceImpl());
		jwsfb.getInInterceptors().add(new LoggingInInterceptor());
		jwsfb.getOutInterceptors().add(new LoggingOutInterceptor());
		jwsfb.create();
		System.out.println("demo9 server ready...");
	}
}
