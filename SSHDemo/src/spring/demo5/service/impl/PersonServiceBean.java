package spring.demo5.service.impl;

import spring.demo5.service.PersonService;

public class PersonServiceBean implements PersonService {

	/**
	 * ��ʼ��������Դ
	 */
	public void init(){
//		System.out.println("init-methodָ���ģ�ʵ���������̵��ã����ڴ���Դ��");
	}
	/* (non-Javadoc)
	 * @see spring.demo4.service.impl.PersonService#save()
	 */
	@Override
	public void save(){
		System.out.println("person�����ˣ�");
	}
	
	/**
	 * ����ǰ�ͷ���Դ
	 */
	public void destory(){
//		System.out.println("�ر���Դ");
	}
	
}
