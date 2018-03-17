package spring.demo7.service.impl;

import spring.demo7.dao.PersonDao;
import spring.demo7.service.PersonService;

/**
 * ��������ע�룬ʹ�ù�������ʽ
 */
public class PersonServiceBean implements PersonService {

	private PersonDao personDao; 
	private String name;
	
	//������
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
