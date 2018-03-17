package hibernate.demo3.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.demo3.domain.User;
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
			throw new RuntimeException(e);
		} finally{
			if(session !=null) session.close();
		}
	}
	
	public User get(int id){
		Session session = null;
		Transaction tx = null;
		User user = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			user = session.get(User.class, id); 
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return user;
		
	}
}
