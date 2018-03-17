package spring.demo11.service.impl;

import org.springframework.stereotype.Service;

import spring.demo11.service.PersonService;

/**
 * AOP
 * 1.��������ҵ�񷽷�
 * 2.�ж��û��Ƿ���Ȩ��
 * 	> ���userΪnull����ʾû��Ȩ�ޣ����ܵ���3������
 * 	> ���user��Ϊnull����ʾ��Ȩ�ޣ����Ե���3��������
 */
@Service("demo11_personService")
public class PersonServiceBean implements PersonService{

	private String user = null;
	
	public PersonServiceBean(){
		
	}
	public PersonServiceBean(String user){
		this.user = user;
	}
	
	@Override
	public void save(String name) {
//		throw new RuntimeException("��ʾ�׳��쳣��ǿ");
		System.out.println("����save");
	}

	@Override
	public void update(String name, Integer id) {
		System.out.println("����update");
	}

	@Override
	public String getPersonName(Integer id) {
		System.out.println("����getPersonName");
		return "xxx";
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

}

