package hibernate.demo7.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo7.domain.User;
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
		user.setName("依然张三");
		user.getAddressMap().put("公司", "XX路人行天桥");
		user.getAddressMap().put("家庭", "XX高速第10个墩的天桥底");
		save(user);
	}
	
	@Test
	public void testGer(){
		User user = get(1);
		System.out.println(user);
	}
}
