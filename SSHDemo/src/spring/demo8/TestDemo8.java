package spring.demo8;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo8.service.PersonService;

/**
 * 使用注解注入bean，@Resource，@Autowried
 */
public class TestDemo8 {

	@Test
	public void test1(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("demo8_personService");
		personService.save();
		ctx.close();
	}
}
