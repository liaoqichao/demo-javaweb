package spring.demo4.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo4.service.PersonService;

/**
 * ����bean�������򡣵�����ԭ�ͣ�request��session��application 
 */
public class TestDemo4 {

	/**
	 * ���bean��ǩû��ָ��scope���ԣ�Ĭ��Ϊscope="singleton".
	 * �����޸�Ϊscope="prototype"
	 */
	@Test
	public void test1(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		//�����õĻ�����ʹ��scope="prototype"
//		PersonService personService1 = ctx.getBean(PersonService.class, "demo4_personService");
//		PersonService personService2 = ctx.getBean(PersonService.class, "demo4_personService");
		
		PersonService personService1 = (PersonService) ctx.getBean("demo4_personService");
		PersonService personService2 = (PersonService) ctx.getBean("demo4_personService");
		System.out.println(personService1 == personService2);//true Ĭ��Ϊ������
	}
}
