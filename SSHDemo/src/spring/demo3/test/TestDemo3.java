package spring.demo3.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo3.service.PersonService;
import spring.demo3.service.PersonServiceFactory;

public class TestDemo3 {

	/**
	 * 测试静态工厂实例化bean
	 */
//	@Test
	public void testStaticFactory(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = ctx.getBean(PersonService.class, "demo3_personService");
		personService.save();
		System.out.println(personService.getClass().getName());
	}
	
	
	/**
	 * 注意配置文件的class属性换成了factory-bean属性
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
