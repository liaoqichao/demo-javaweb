package hibernate.demo6.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo6.domain.User;
import lqcUtils.HibernateUtils;

public class UserDao {

	/**
	 * ��
	 */
	public void save(User user){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally{
			if(session != null) session.close();
		}
	}
	
	/**
	 * ȡ
	 * @param id
	 * @return
	 */
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
		user.getAddressList().add("��1");
		user.getAddressList().add("��2");
		user.getAddressList().add("��1");//ListԪ�ؿ����ظ�
		save(user);
	}
	
	@Test
	public void testGet(){
		User user = get(1);
		if(user != null) System.out.println(user);
	}
}
