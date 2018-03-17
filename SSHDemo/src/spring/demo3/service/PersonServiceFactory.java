package spring.demo3.service;

import spring.demo3.service.impl.PersonServiceBean;

/**
 * 1.使用静态工厂实例化Bean
 * 2.使用实例化工厂来实例化bean
 *
 */
public class PersonServiceFactory {

	//1.使用静态工厂实例化Bean
	public static PersonServiceBean createPersonServiceBean(){
		return new PersonServiceBean();
	}
	//2.使用实例化工厂来实例化bean
	public PersonServiceBean createPersonServiceBean2(){
		return new PersonServiceBean();
	}
}
