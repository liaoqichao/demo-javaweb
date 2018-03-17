package spring.demo5.service;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * lazy-init����
 * init-method����
 * @author Administrator
 *
 */
public class TestDemo5 {

	@Test
	public void test1(){
		//Ҫ����close�������ر�spring��Դ����������ҪΪAbstractApplicationContext
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("demo5_personService");
		ctx.close();//�ر�spring��Դ������personService#destory()
	}
}
