package spring.demo2.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo2.service.PersonService;

/**
 * 获取被spring管理的对象。
 */
public class SpringTest {

	@Test
	public void save(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = ctx.getBean(PersonService.class,"personService");
		personService.save();
	}
}
