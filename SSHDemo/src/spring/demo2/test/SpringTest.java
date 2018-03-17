package spring.demo2.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo2.service.PersonService;

/**
 * ��ȡ��spring����Ķ���
 */
public class SpringTest {

	@Test
	public void save(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = ctx.getBean(PersonService.class,"personService");
		personService.save();
	}
}
