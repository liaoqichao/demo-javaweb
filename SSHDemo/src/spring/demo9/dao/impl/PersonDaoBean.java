package spring.demo9.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Repository;

import spring.demo9.dao.PersonDao;

@Repository
public class PersonDaoBean implements PersonDao{

	@PostConstruct				//EJB3的注解
	public void init(){	//用注解指定初始化方法
//		System.out.println("打开资源");
	}
	
	@Override
	public void add(){
		System.out.println("personDaoBean#add");
	}
	
	@PreDestroy
	public void destory(){
//		System.out.println("销毁前先关闭资源");
	}
}
