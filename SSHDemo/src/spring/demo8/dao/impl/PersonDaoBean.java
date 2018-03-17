package spring.demo8.dao.impl;

import spring.demo8.dao.PersonDao;

public class PersonDaoBean implements PersonDao{

	@Override
	public void add(){
		System.out.println("personDaoBean#add");
	}
}
