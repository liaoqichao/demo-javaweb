package spring.demo3.service;

import spring.demo3.service.impl.PersonServiceBean;

/**
 * 1.ʹ�þ�̬����ʵ����Bean
 * 2.ʹ��ʵ����������ʵ����bean
 *
 */
public class PersonServiceFactory {

	//1.ʹ�þ�̬����ʵ����Bean
	public static PersonServiceBean createPersonServiceBean(){
		return new PersonServiceBean();
	}
	//2.ʹ��ʵ����������ʵ����bean
	public PersonServiceBean createPersonServiceBean2(){
		return new PersonServiceBean();
	}
}
