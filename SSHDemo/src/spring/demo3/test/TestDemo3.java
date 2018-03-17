package spring.demo3.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo3.service.PersonService;
import spring.demo3.service.PersonServiceFactory;

public class TestDemo3 {

	/**
	 * ���Ծ�̬����ʵ����bean
	 */
//	@Test
	public void testStaticFactory(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = ctx.getBean(PersonService.class, "demo3_personService");
		personService.save();
		System.out.println(personService.getClass().getName());
	}
	
	
	/**
	 * ע�������ļ���class���Ի�����factory-bean����
	 */
	@Test
	public void testFactory(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonServiceFactory personServiceFactory = 
				ctx.getBean(PersonServiceFactory.class, "demo3_personServiceFactory2");
		PersonService personService = personServiceFactory.createPersonServiceBean2(); 
		personService.save();
		System.out.println(personService.getClass().getName());
	}
}
