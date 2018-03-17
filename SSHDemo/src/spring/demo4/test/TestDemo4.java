package spring.demo4.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo4.service.PersonService;

/**
 * 测试bean的作用域。单例，原型，request，session，application 
 */
public class TestDemo4 {

	/**
	 * 如果bean标签没有指定scope属性，默认为scope="singleton".
	 * 这里修改为scope="prototype"
	 */
	@Test
	public void test1(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		//这样用的话不能使用scope="prototype"
//		PersonService personService1 = ctx.getBean(PersonService.class, "demo4_personService");
//		PersonService personService2 = ctx.getBean(PersonService.class, "demo4_personService");
		
		PersonService personService1 = (PersonService) ctx.getBean("demo4_personService");
		PersonService personService2 = (PersonService) ctx.getBean("demo4_personService");
		System.out.println(personService1 == personService2);//true 默认为单例咯
	}
}
