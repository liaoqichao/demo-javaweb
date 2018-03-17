package spring.demo8.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import spring.demo8.dao.PersonDao;
import spring.demo8.service.PersonService;

/**
 * ��������ע�룬ʹ��Field��ʽ
 */
public class PersonServiceBean implements PersonService {

//	@Resource(name="demo8_personDao")
	@Autowired(required=false)//Ϊtrue��ʾ����ע������Ա�����ֵ�����ʵ���Ҳ������׳��쳣������Ϊfalse����Ҳ���������Ϊnull
	@Qualifier("demo8_personDao")//�޸�autowired��Ĭ�ϰ���װ�䷽ʽ����Ϊ������װ��
	private PersonDao personDao; 
	
	//Ҳ������set������ʹ��@Resource
	@Resource(name="demo8_personDao")
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	private String name;
	
	
	
	@Override
	public void save() {
		 personDao.add();
		 System.out.println(name);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
