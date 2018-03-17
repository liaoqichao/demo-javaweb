package spring.demo9;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.demo9.dao.PersonDao;
import spring.demo9.service.PersonService;

/**
 * spring�Զ�ɨ��͹���bean
 */
public class TestDemo9 {

	@Test
	public void test1(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService ps1 = (PersonService) ctx.getBean("demo9_personService");
		PersonService ps2 = (PersonService) ctx.getBean("demo9_personService");
		//��ָ���쳣��û��personDao����Ҫ��ע���personServiceע��personDao
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
