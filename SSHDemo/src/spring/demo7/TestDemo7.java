package spring.demo7;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo7.service.PersonService;

/**
 * ʹ�ù������ķ�ʽ����ע��
 */
public class TestDemo7 {

	@Test
	public void test1(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("demo7_personService");
		personService.save();
		ctx.close();
	}
}
