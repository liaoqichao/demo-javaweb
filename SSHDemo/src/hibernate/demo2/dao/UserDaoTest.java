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
		user.setName("����");
		userDao.save(user);
	}

	// @Test
	public void testDelete() {
		userDao.delete(3);
	}

	// @Test
	public void testUpdate() {
		// �����ݿ��л�ȡһ�����ڵ�����
		User user = userDao.getById(2);
		// ����
		user.setName("�ҽ�����");
		userDao.update(user);
	}

	// @Test
	public void testGetById() {
		User user = userDao.getById(2);
		System.out.println(user);
	}

	/////////////////////////////////////////////////////

//	@Test
	public void testSave_25() {// ����25����¼��Ϊ��ҳ��׼��
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
	public void testFindAllIntInt(){//���Է�ҳ
		//��ѯ
//		QueryResult<User> qr = userDao.findAll(0, 10);//��һҳ��ÿҳ10��
//		QueryResult<User> qr = userDao.findAll(10, 10);//�ڶ�ҳ��ÿҳ10��
		QueryResult<User> qr = userDao.findAll(20, 10);//����ҳ��ÿҳ10��
		//��ʾ
		System.out.println("�ܼ�¼����"+ qr.getTr());
		for (User user : qr.getBeanList()) {
			System.out.println(user);
		}
	}
}
