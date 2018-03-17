package demo12.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService psersonService = (PersonService) ctx.getBean("personService");
		Person p = psersonService.findPersonById(3);
		// 不能sysout(p)，因为生成的代码里面没有toString方法，还是Object的
		System.out.println(p.getId()+" : "+p.getName());
	}
}
