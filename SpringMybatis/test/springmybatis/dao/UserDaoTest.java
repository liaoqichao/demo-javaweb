package springmybatis.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springmybatis.po.User;

public class UserDaoTest {

	private ApplicationContext ctx;
	// 得到Spring容器
	@Before
	public void setUp() throws Exception {
		this.ctx = new ClassPathXmlApplicationContext("classpath:spring/beans.xml");
	}

	@Test
	public void testFindUserById() throws Exception {
		UserDao userDao = (UserDao) ctx.getBean("userDao");
		User user = userDao.findUserById(1);
		System.err.println(user);
	}

//	@Test
	public void testInsertUser() throws Exception {
		UserDao userDao = (UserDao) ctx.getBean("userDao");
		User user = new User();
		user.setUsername("mybatis整合spring3");
		user.setSex("1");
		userDao.insertUser(user);
	}

//	@Test
	public void testDeleteUser() throws Exception {
		UserDao userDao = (UserDao) ctx.getBean("userDao");
		userDao.deleteUser(16);
	}

	@Test
	public void testFindUsersByName() throws Exception {
		UserDao userDao = (UserDao) ctx.getBean("userDao");
		List<User> userList = userDao.findUsersByName("三");
		for (User user : userList) {
			System.err.println(user);
		}
	}

}
