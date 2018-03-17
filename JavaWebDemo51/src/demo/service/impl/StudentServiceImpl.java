package demo.service.impl;

import demo.dao.StudentDao;
import demo.service.StudentService;

public class StudentServiceImpl implements StudentService {
	private StudentDao studentDao = null;//现在不允许这么写了,谁调用StudentServiceImpl谁在配置文件提供StudentDao实例

	public StudentDao getStudentDao() {
		return studentDao;
	}

	/*
	 * 以后service需要通过setDao来设置dao，只要在配置文件配好beanFactory就能一键封装！
	 * 同理servlet也需要有setService方法。但是存在问题！之前写的都是把自己创建的移交到beanFactory创建。
	 * 但是servlet是tomcat创建的!通过struct和spring可以整合可以配置beanFactory创建servlet。
	 * 这样依赖的注入都在配置文件中完成！
	 */
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public void login() {
		studentDao.add(null);
		studentDao.update(null);
	}
	

}
