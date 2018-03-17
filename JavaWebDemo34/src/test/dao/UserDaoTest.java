package test.dao;

import org.junit.Test;

import user.dao.UserDao;
import user.dao.UserDaoImpl;
import user.domain.User;

/**
 * ����Dao
 * @author Administrator
 *
 */
public class UserDaoTest {

	/*
	 * ���Է������ƣ�test+�����Է�������
	 * ���Է�������û�з���ֵ,û�в���
	 */
	@Test
	public void testFindByUsername(){
		UserDao userDao = new UserDaoImpl();
		User user = userDao.findByUsername("����");
		System.out.println(user);
	}
	@Test
	public void testAdd(){
		UserDao userDao = new UserDaoImpl();
		User user = new User();
		user.setUsername("����");
		user.setPassword("wangwu");
		userDao.addUser(user);
	}
}
