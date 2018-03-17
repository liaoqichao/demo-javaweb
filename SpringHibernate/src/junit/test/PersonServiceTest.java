package junit.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sh.domain.Person;
import sh.service.PersonService;

public class PersonServiceTest {

	private static PersonService personService ;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
			personService = (PersonService)ctx.getBean("personService");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//	@Test
	public void testSave() {
		Person person = new Person();
		person.setName("钱七");
		personService.save(person);
	}

//	@Test
	public void testUpdate() {
		Person person = personService.getPerson(2);
		person.setName("张三三");
		personService.update(person);
	}

//	@Test
	public void testGetPerson() {
		System.out.println(personService.getPerson(2));
	}

//	@Test
	public void testDelete() {
		personService.delete(3);
	}

//	@Test
	public void testGetPersonList() {
		System.out.println(personService.getPersonList());
	}
	
	@Test
	public void testEHCache(){
	
		/*
		 * 如果不使用二级缓存，恢复后会报错
		 */
		List<Person> personList = personService.getPersonList();
		System.out.println(personList.size());
		System.out.println("开始");
		System.out.println(personService.getPerson(68));

		try {
			System.out.println("停止");
			Thread.sleep(15*1000);
			//这里打开cmd 输入 net stop mysql5
			System.out.println("恢复");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(personService.getPerson(68));
		
	}

}
