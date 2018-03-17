package spring.demo6;

import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo6.service.PersonService;

public class TestDemo6 {

	/**
	 * ������ע��
	 */
//	@Test
	public void test1(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("deom6_personService");
		personService.save();
		ctx.close();
	}
	
	/**
	 * ע�뼯�����͵Ķ���
	 * ��������Ϊset
	 */
//	@Test 
	public void test2(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("deom6_personService");
		System.out.println(personService.getSet());
		ctx.close();
	}
	/**
	 * ע�뼯�����͵Ķ���
	 * ��������Ϊlist
	 */
//	@Test 
	public void test3(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("deom6_personService");
		System.out.println(personService.getList());
		ctx.close();
	}
	/**
	 * ע�뼯�����͵Ķ���
	 * ��������Ϊproperties
	 */
	@Test 
	public void test4(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("deom6_personService");
		Properties props = personService.getProps();
		for(Object key : props.keySet()){
			System.out.println(props.getProperty((String) key));
			//���value3,value2,value1
		}
		ctx.close();
	}
	/**
	 * ע�뼯�����͵Ķ���
	 * ��������Ϊmap
	 */
	@Test 
	public void test5(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("deom6_personService");
		Map<String,String> map = personService.getMap();
		for ( Map.Entry<String, String> entry: map.entrySet()) {
			System.out.println(entry.getKey()+" = "+entry.getValue());
		}
		ctx.close();
	}
}
