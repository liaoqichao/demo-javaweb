package demo12.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService psersonService = (PersonService) ctx.getBean("personService");
		Person p = psersonService.findPersonById(3);
		// ����sysout(p)����Ϊ���ɵĴ�������û��toString����������Object��
		System.out.println(p.getId()+" : "+p.getName());
	}
}
