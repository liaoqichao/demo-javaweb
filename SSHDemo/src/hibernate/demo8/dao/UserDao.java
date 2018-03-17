package hibernate.demo8.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo8.domain.User;
import lqcUtils.HibernateUtils;

public class UserDao {

	/**
	 * 
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
			throw new RuntimeException(e);
		} finally{
			session.close();
		}
	}
	
	/**
	 * 
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
			session.close();
		}
	}
	
	@Test
	public void testSave(){
		User user = new User();
		user.setName("ÍõÎå");
		user.getAddressSet().add("ËÕÌúÐù");
		user.getAddressSet().add("Ê¯ÄÏÐù");
		user.getAddressSet().add("×ÏÌ´Ðù");
		save(user);
		
	}
	
	@Test
	public void testGet(){
		User user = get(3);
		System.out.println(user);
	}
}
