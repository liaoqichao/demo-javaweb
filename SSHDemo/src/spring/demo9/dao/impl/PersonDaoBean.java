package spring.demo9.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Repository;

import spring.demo9.dao.PersonDao;

@Repository
public class PersonDaoBean implements PersonDao{

	@PostConstruct				//EJB3��ע��
	public void init(){	//��ע��ָ����ʼ������
//		System.out.println("����Դ");
	}
	
	@Override
	public void add(){
		System.out.println("personDaoBean#add");
	}
	
	@PreDestroy
	public void destory(){
//		System.out.println("����ǰ�ȹر���Դ");
	}
}
