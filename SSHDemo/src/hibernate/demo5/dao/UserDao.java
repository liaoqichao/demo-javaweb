package hibernate.demo5.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo5.domain.User;
import lqcUtils.HibernateUtils;

/**
 * bean�����м��ϣ�set��
 */
public class UserDao {
	
	/**
	 * ��������
	 * @param user
	 */
	public void save(User user){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			//
			session.save(user);
			//
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session != null) session.close();
		}
	}
	
	
	public User get(int id){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			User user = session.get(User.class, id);
			tx.commit();
			return user;
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session != null) session.close();
		}
	}
	@Test
	public void testSave(){
		User user = new User();
		user.setName("����");
		user.getAddressSet().add("�����ļ�");
		user.getAddressSet().add("��������");
		save(user);
	}
	
	@Test
	public void testGet(){
		User user = get(4);
		System.out.println(user);
	}
}
