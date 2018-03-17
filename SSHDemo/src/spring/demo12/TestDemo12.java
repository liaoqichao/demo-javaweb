package spring.demo12;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo12.dao.PersonDao;
import spring.demo12.domain.Person;

public class TestDemo12 {
	private static AbstractApplicationContext ctx ;
	private static PersonDao personDao;	//����Ҫ�ýӿ���ʽ������ʹ�������ʱ�򣬴��������������jdk��ʽ������û�нӿھͱ���
	static{
		ctx = new ClassPathXmlApplicationContext("beans.xml");
		personDao = (PersonDao) ctx.getBean("demo12_personDao");
	}
	
//	@Test
	public void testSave(){
		Person person = new Person();
		person.setUsername("����");
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
		person.setUsername("������");
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
