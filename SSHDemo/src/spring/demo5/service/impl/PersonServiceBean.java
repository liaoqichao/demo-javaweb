package spring.demo5.service.impl;

import spring.demo5.service.PersonService;

public class PersonServiceBean implements PersonService {

	/**
	 * 初始化，打开资源
	 */
	public void init(){
//		System.out.println("init-method指定的，实例化后立刻调用，用于打开资源。");
	}
	/* (non-Javadoc)
	 * @see spring.demo4.service.impl.PersonService#save()
	 */
	@Override
	public void save(){
		System.out.println("person保存了！");
	}
	
	/**
	 * 销毁前释放资源
	 */
	public void destory(){
//		System.out.println("关闭资源");
	}
	
}
