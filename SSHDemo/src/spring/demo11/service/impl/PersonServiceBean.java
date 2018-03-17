package spring.demo11.service.impl;

import org.springframework.stereotype.Service;

import spring.demo11.service.PersonService;

/**
 * AOP
 * 1.拦截所有业务方法
 * 2.判断用户是否有权限
 * 	> 如果user为null，表示没有权限，不能调用3个方法
 * 	> 如果user不为null，表示又权限，可以调用3个方法。
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
//		throw new RuntimeException("演示抛出异常增强");
		System.out.println("我是save");
	}

	@Override
	public void update(String name, Integer id) {
		System.out.println("我是update");
	}

	@Override
	public String getPersonName(Integer id) {
		System.out.println("我是getPersonName");
		return "xxx";
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

}

