package demo5;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


/**
 * ͨ���ͻ��˱�̷�ʽ����WebService
 */
public class Client {
	public static void main(String[] args) throws Exception {
		/*
		 * 1.ͨ��Service.create(wsdlDocumentLocation, serviceName);�õ�service����
		 * 	> ����1��wsdl�ļ���λ�ã�URL����
		 *  > ����2��java.xml.namespace.QName����.QName��ʾXML�е��޶����ƣ�QName��ֵ���������ƿռ䣬URI�����ز��֡�ǰ׺�����൱��Ӣ���еĶ����XXX�ġ������޶�
		 *  	- QName(namespace,servicename);
		 *  	name:wsdl�ļ���definitions��ǩ��targetNamespace���Ե�ֵhttp://ws.demo2/
		 *  	servicename:Ҫ���õķ���HelloServiceService��service��ǩname���Ե�ֵ
		 * 2.����s.getPort(portName, serviceEndpointInterface)����
		 *  > ��һ������QName����
		 *  	- ��һ�����������Ǹղŵ����ƿռ�"http://ws.demo2/"
		 *  	- �ڶ���������service��ǩ��port��ǩ��name���Ե�ֵ
		 *  > �ڶ������� 
		 *  	- class<T>���ͣ�����ָ����������.����ָ��wsimport���ɵ�HelloService.java��
		 *  	      �������Ƿ����ṩ�߱�@WebServiceע�����
		 *  > ����T����
		 *  3.�����ṩ����ķ���.helloService.sayHello("����");
		 */
		URL wsdlUrl = new URL("http://127.0.0.1:6789/hello?wsdl");
		QName serviceName = new QName("http://ws.demo2/","HelloServiceService");// ͨ��QName��ָ���ǵ��������ַ���ĸ�����
		Service helloServiceService = Service.create(wsdlUrl, serviceName);
		HelloService helloService = helloServiceService.getPort(new QName("http://ws.demo2/","HelloServicePort"), HelloService.class);
		String str = helloService.sayHello("����");
		System.out.println(str);
	}
}
