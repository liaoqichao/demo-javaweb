package spring.demo6;

import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo6.service.PersonService;

public class TestDemo6 {

	/**
	 * 给属性注入
	 */
//	@Test
	public void test1(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("deom6_personService");
		personService.save();
		ctx.close();
	}
	
	/**
	 * 注入集合类型的对象
	 * 集合类型为set
	 */
//	@Test 
	public void test2(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("deom6_personService");
		System.out.println(personService.getSet());
		ctx.close();
	}
	/**
	 * 注入集合类型的对象
	 * 集合类型为list
	 */
//	@Test 
	public void test3(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("deom6_personService");
		System.out.println(personService.getList());
		ctx.close();
	}
	/**
	 * 注入集合类型的对象
	 * 集合类型为properties
	 */
	@Test 
	public void test4(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService personService = (PersonService) ctx.getBean("deom6_personService");
		Properties props = personService.getProps();
		for(Object key : props.keySet()){
			System.out.println(props.getProperty((String) key));
			//输出value3,value2,value1
		}
		ctx.close();
	}
	/**
	 * 注入集合类型的对象
	 * 集合类型为map
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
