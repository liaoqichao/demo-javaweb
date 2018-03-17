package spring.demo11;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo11.service.PersonService;

public class TestDemo11 {

	@Test
	public void testMyInterceptor(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		
		PersonService personService = (PersonService) ctx.getBean("demo11_personService");
//		personService.save("123");
//		personService.update("уехЩ", 555);
		personService.getPersonName(123);
		ctx.close();
	}
}
