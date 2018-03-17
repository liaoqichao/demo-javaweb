package hibernate.demo6.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo6.domain.User;
import lqcUtils.HibernateUtils;

public class UserDao {

	/**
	 * 存
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
	 * 取
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
		user.setName("张三");
		user.getAddressList().add("家1");
		user.getAddressList().add("家2");
		user.getAddressList().add("家1");//List元素可以重复
		save(user);
	}
	
	@Test
	public void testGet(){
		User user = get(1);
		if(user != null) System.out.println(user);
	}
}
