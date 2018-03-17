package spring.demo9.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import spring.demo9.dao.PersonDao;
import spring.demo9.service.PersonService;

/**
 * �Զ�ɨ���bean����
 */
@Service("demo9_personService" )//ָ������
@Scope("prototype")	//ָ��������
public class PersonServiceBean implements PersonService {

	@Resource
	private PersonDao personDao; 
	private String name = "����";
	
	@Override
	public void save() {
		 personDao.add();
		 System.out.println(name);
	}

}
