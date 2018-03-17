package demo5;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


/**
 * 通过客户端编程方式调用WebService
 */
public class Client {
	public static void main(String[] args) throws Exception {
		/*
		 * 1.通过Service.create(wsdlDocumentLocation, serviceName);得到service对象
		 * 	> 参数1：wsdl文件的位置，URL类型
		 *  > 参数2：java.xml.namespace.QName类型.QName表示XML中的限定名称，QName的值包含（名称空间，URI、本地部分、前缀）。相当于英语中的定语，“XXX的”用来限定
		 *  	- QName(namespace,servicename);
		 *  	name:wsdl文件的definitions标签的targetNamespace属性的值http://ws.demo2/
		 *  	servicename:要调用的服务HelloServiceService。service标签name属性的值
		 * 2.调用s.getPort(portName, serviceEndpointInterface)方法
		 *  > 第一个参数QName类型
		 *  	- 第一个参数，还是刚才的名称空间"http://ws.demo2/"
		 *  	- 第二个参数，service标签下port标签的name属性的值
		 *  > 第二个参数 
		 *  	- class<T>类型，用于指定返回类型.用于指定wsimport生成的HelloService.java。
		 *  	      这个类就是服务提供者被@WebService注解的类
		 *  > 返回T类型
		 *  3.调用提供服务的方法.helloService.sayHello("张三");
		 */
		URL wsdlUrl = new URL("http://127.0.0.1:6789/hello?wsdl");
		QName serviceName = new QName("http://ws.demo2/","HelloServiceService");// 通过QName来指定是调用上面地址的哪个服务
		Service helloServiceService = Service.create(wsdlUrl, serviceName);
		HelloService helloService = helloServiceService.getPort(new QName("http://ws.demo2/","HelloServicePort"), HelloService.class);
		String str = helloService.sayHello("李四");
		System.out.println(str);
	}
}
