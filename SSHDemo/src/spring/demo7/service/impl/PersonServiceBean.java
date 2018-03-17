package spring.demo7.service.impl;

import spring.demo7.dao.PersonDao;
import spring.demo7.service.PersonService;

/**
 * 属性依赖注入，使用构造器方式
 */
public class PersonServiceBean implements PersonService {

	private PersonDao personDao; 
	private String name;
	
	//构造器
	public PersonServiceBean(PersonDao personDao, String name) {
		super();
		this.personDao = personDao;
		this.name = name;
	}
	
	
	
	@Override
	public void save() {
		 personDao.add();
		 System.out.println(name);
	}


}
