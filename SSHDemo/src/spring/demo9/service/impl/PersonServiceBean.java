package spring.demo9.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import spring.demo9.dao.PersonDao;
import spring.demo9.service.PersonService;

/**
 * 自动扫描和bean管理
 */
@Service("demo9_personService" )//指定名称
@Scope("prototype")	//指定作用域
public class PersonServiceBean implements PersonService {

	@Resource
	private PersonDao personDao; 
	private String name = "张三";
	
	@Override
	public void save() {
		 personDao.add();
		 System.out.println(name);
	}

}
