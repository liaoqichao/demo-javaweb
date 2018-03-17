package test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.Demo3_UserDao;
import dao.impl.Demo3_UserDaoImpl;
import mybatis.po.User;

public class Demo3_UserDaoTest {

	private Demo3_UserDao userDao;
	@Before
	public void setUp() throws Exception {
		this.userDao = new Demo3_UserDaoImpl();
	}

//	@Test
	public void testFindUserById() throws Exception {
		User user = userDao.findUserById(1);
		System.out.println(user);
	}

	@Test
	public void testInsertUser() throws Exception {
		User user = new User();
		user.setUsername("ÌáÄª");
		user.setSex("1");
		user.setBirthday(new Date());
		user.setAddress("ÕÙ»½Ê¦Ï¿¹È");
		
		userDao.insertUser(user);
	}

//	@Test
	public void testDeleteUser() throws Exception {
		userDao.deleteUser(16);
	}
	
	@Test
	public void testFindUsersByName() throws Exception{
		List<User> userList = userDao.findUsersByName("Èý");
		for (User user : userList) {
			System.err.println(user);
		}
	}

}
