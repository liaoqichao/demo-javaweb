package demo7;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.cxf.frontend.ServerFactoryBean;

/**
 * 使用ServerFactoryBean发布CXF的Web服务
 * @author Liaoqc
 *
 */
public class HelloService {

	public static void main(String[] args) {
		// 1. 实例化工厂类
		ServerFactoryBean sfb = new ServerFactoryBean();
		
		// 2. 设置服务的发布地址
		sfb.setAddress("http://127.0.0.1:6789/hello");
		
		// 3. 设置提供服务的类的类型
		sfb.setServiceClass(HelloService.class);
		
		// 4. 设置提供服务的实例
		sfb.setServiceBean(new HelloService());
		
		// 5. 发布服务。相当于Endpoint.publish("http://127.0.0.1:6789/hello", new HelloService());方法
		sfb.create();
		System.out.println("Server ready...");
		// 浏览器打开http://127.0.0.1:6789/hello?wsdl是否发布成功
		
		/*
		 * 使用Endpoint.publish + WebService注解，必须要有发布的方法，如果没有可以发布的方法则报错。
		 * 而这里使用CXF没有发布方法也不会报错。
		 */
	}
	
	public String sayHello(String name){
		System.out.println("sayHello..name="+name);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date())+" :hello "+name;
	}
}
