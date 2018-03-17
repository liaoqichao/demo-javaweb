package demo10.client1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ͨ��spring�����ļ��ķ�ʽ����WebService
 * @author Liaoqc
 *
 */
public class App {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("ClientBeans.xml");
		HelloService service = (HelloService) ctx.getBean("helloService");
		// service�Ǵ������ Spring����ʹ��cglib��jdk�Ķ�̬����
		String str = service.sayHello("����");
		System.out.println(str);
	}
}
