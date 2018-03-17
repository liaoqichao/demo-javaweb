package spring.demo8.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import spring.demo8.dao.PersonDao;
import spring.demo8.service.PersonService;

/**
 * 属性依赖注入，使用Field方式
 */
public class PersonServiceBean implements PersonService {

//	@Resource(name="demo8_personDao")
	@Autowired(required=false)//为true表示，被注解的属性必须有值，如果实在找不到就抛出异常。设置为false如果找不到就设置为null
	@Qualifier("demo8_personDao")//修改autowired的默认按类装配方式，变为按名称装配
	private PersonDao personDao; 
	
	//也可以在set方法上使用@Resource
	@Resource(name="demo8_personDao")
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	private String name;
	
	
	
	@Override
	public void save() {
		 personDao.add();
		 System.out.println(name);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
