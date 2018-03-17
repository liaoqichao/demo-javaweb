package spring.demo9;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo9.dao.PersonDao;
import spring.demo9.service.PersonService;

/**
 * spring自动扫描和管理bean
 */
public class TestDemo9 {

	@Test
	public void test1(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService ps1 = (PersonService) ctx.getBean("demo9_personService");
		PersonService ps2 = (PersonService) ctx.getBean("demo9_personService");
		//空指针异常，没有personDao，需要用注解给personService注入personDao
		ps1.save();
		System.out.println(ps1 == ps2);//false
		ctx.close();
	}
	
	@Test
	public void test2(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonDao pd = (PersonDao) ctx.getBean("personDaoBean");
		pd.add();
		ctx.close();
	}
}
