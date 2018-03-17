package spring.demo6.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import spring.demo6.dao.PersonDao;
import spring.demo6.service.PersonService;

/**
 * 属性依赖注入，使用setter方式
 */
public class PersonServiceBean implements PersonService {

	// 先声明，但是还没注入
	private PersonDao personDao;

	// 给集合类型注入对象
	private Set<String> set = new HashSet<String>();
	private List<String> list = new ArrayList<String>();
	
	// Properties属性
	private Properties props = new Properties();
	
	// Map属性
	private Map<String,String> map = new HashMap<String,String>();
	

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	// spring通过这个方法进行依赖注入
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public void save() {
		System.out.println("save-start");
		 personDao.add();
		System.out.println("save-end");
	}

}
