package demo10.client1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过spring配置文件的方式调用WebService
 * @author Liaoqc
 *
 */
public class App {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("ClientBeans.xml");
		HelloService service = (HelloService) ctx.getBean("helloService");
		// service是代理对象。 Spring大量使用cglib和jdk的动态代理
		String str = service.sayHello("李四");
		System.out.println(str);
	}
}
