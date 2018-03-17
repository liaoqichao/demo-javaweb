package demo7;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.cxf.frontend.ServerFactoryBean;

/**
 * ʹ��ServerFactoryBean����CXF��Web����
 * @author Liaoqc
 *
 */
public class HelloService {

	public static void main(String[] args) {
		// 1. ʵ����������
		ServerFactoryBean sfb = new ServerFactoryBean();
		
		// 2. ���÷���ķ�����ַ
		sfb.setAddress("http://127.0.0.1:6789/hello");
		
		// 3. �����ṩ������������
		sfb.setServiceClass(HelloService.class);
		
		// 4. �����ṩ�����ʵ��
		sfb.setServiceBean(new HelloService());
		
		// 5. ���������൱��Endpoint.publish("http://127.0.0.1:6789/hello", new HelloService());����
		sfb.create();
		System.out.println("Server ready...");
		// �������http://127.0.0.1:6789/hello?wsdl�Ƿ񷢲��ɹ�
		
		/*
		 * ʹ��Endpoint.publish + WebServiceע�⣬����Ҫ�з����ķ��������û�п��Է����ķ����򱨴�
		 * ������ʹ��CXFû�з�������Ҳ���ᱨ��
		 */
	}
	
	public String sayHello(String name){
		System.out.println("sayHello..name="+name);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date())+" :hello "+name;
	}
}
