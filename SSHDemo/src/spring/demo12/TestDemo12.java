package spring.demo12;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo12.dao.PersonDao;
import spring.demo12.domain.Person;

public class TestDemo12 {
	private static AbstractApplicationContext ctx ;
	private static PersonDao personDao;	//这里要用接口形式，不用使用事务的时候，创建代理对象是用jdk方式创建，没有接口就报错。
	static{
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		personDao = (PersonDao) ctx.getBean("demo12_personDao");
	}
	
//	@Test
	public void testSave(){
		Person person = new Person();
		person.setUsername("王八");
		personDao.save(person);
	}
	
//	@Test
	public void testDelete(){
		try {
			personDao.delete(5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@Test
	public void testUpdate(){
		Person person = personDao.getPerson(6);
		person.setUsername("赵六六");
		personDao.update(person);
	}
	
	@Test
	public void testGetPerson(){
		Person person = personDao.getPerson(5);
		System.out.println(person);
	}
	
//	@Test	
	public void testGetPersonList(){
		List<Person> personList = personDao.getPersonList();
		System.out.println(personList);
		
	}
}
