package hibernate.demo2.dao;

import java.util.List;

import org.junit.Test;

import hibernate.demo2.domain.QueryResult;
import hibernate.demo2.domain.User;

public class UserDaoTest {

	private UserDao userDao = new UserDao();

//	 @Test
	public void testSave() {
		User user = new User();
		user.setName("张三");
		userDao.save(user);
	}

	// @Test
	public void testDelete() {
		userDao.delete(3);
	}

	// @Test
	public void testUpdate() {
		// 从数据库中获取一条存在的数据
		User user = userDao.getById(2);
		// 更新
		user.setName("我叫王五");
		userDao.update(user);
	}

	// @Test
	public void testGetById() {
		User user = userDao.getById(2);
		System.out.println(user);
	}

	/////////////////////////////////////////////////////

//	@Test
	public void testSave_25() {// 插入25条记录，为分页做准备
		for (int i = 1; i <= 25; i++) {
			User user = new User();
			user.setName("test_" + i);
			userDao.save(user);
		}
	}

	 @Test
	public void testFindAll() {
		List<User> userList = userDao.findAll();
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
//	@Test
	public void testFindAllIntInt(){//测试分页
		//查询
//		QueryResult<User> qr = userDao.findAll(0, 10);//第一页，每页10条
//		QueryResult<User> qr = userDao.findAll(10, 10);//第二页，每页10条
		QueryResult<User> qr = userDao.findAll(20, 10);//第三页，每页10条
		//显示
		System.out.println("总记录数："+ qr.getTr());
		for (User user : qr.getBeanList()) {
			System.out.println(user);
		}
	}
}
