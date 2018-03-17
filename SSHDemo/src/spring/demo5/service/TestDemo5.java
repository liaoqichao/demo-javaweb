package spring.demo5.service;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * lazy-init属性
 * init-method属性
 * @author Administrator
 *
 */
public class TestDemo5 {

	@Test
	public void test1(){
		//要调用close方法，关闭spring资源，引用类型要为AbstractApplicationContext
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("demo5_personService");
		ctx.close();//关闭spring资源，调用personService#destory()
	}
}
