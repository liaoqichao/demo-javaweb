package test.dao;

import org.junit.Test;

import user.dao.UserDao;
import user.dao.UserDaoImpl;
import user.domain.User;

/**
 * 测试Dao
 * @author Administrator
 *
 */
public class UserDaoTest {

	/*
	 * 测试方法名称：test+被测试方法名称
	 * 测试方法必须没有返回值,没有参数
	 */
	@Test
	public void testFindByUsername(){
		UserDao userDao = new UserDaoImpl();
		User user = userDao.findByUsername("赵六");
		System.out.println(user);
	}
	@Test
	public void testAdd(){
		UserDao userDao = new UserDaoImpl();
		User user = new User();
		user.setUsername("王五");
		user.setPassword("wangwu");
		userDao.addUser(user);
	}
}
