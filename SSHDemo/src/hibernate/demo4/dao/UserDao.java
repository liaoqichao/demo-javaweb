package hibernate.demo4.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo4.domain.User;
import lqcUtils.HibernateUtils;

public class UserDao {

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
			throw e;
		} finally{
			session.close();
		}
	}
	
	@Test
	public void testSave(){
		
		User user = new User();
		user.setName("ÍõÎå");
		save(user);
	}
}
