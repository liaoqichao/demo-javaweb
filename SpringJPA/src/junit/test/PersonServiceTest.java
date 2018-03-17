package junit.test;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sj.domain.Person;
import sj.service.PersonService;

public class PersonServiceTest {

	private static PersonService personService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		personService = (PersonService) ctx.getBean("personService");
	}

//	@Test
	public void testSave() {
		Person person = new Person("����");
		personService.save(person);
	}

//	@Test
	public void testUpdate() {
		Person person = new Person();
		person.setId(1);
		person.setName("������");
		personService.update(person);
	}

//	@Test
	public void testDelete() {
		personService.delete(2);
	}

	@Test
	public void testGetPerson() {
		/*
		 * �����������쳣 
		 */
		Person person = personService.getPerson(1);
		System.out.println(person);
	}

//	@Test
	public void testGetPersonList() {
		//û�г����������쳣
		List<Person> personList = personService.getPersonList();
		for (Person person : personList) {
			System.out.println(person);
		}
	}

}
