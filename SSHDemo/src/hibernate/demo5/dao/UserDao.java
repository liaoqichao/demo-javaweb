package hibernate.demo5.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo5.domain.User;
import lqcUtils.HibernateUtils;

/**
 * bean里面有集合（set）
 */
public class UserDao {
	
	/**
	 * 保存数据
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
		user.setName("赵六");
		user.getAddressSet().add("赵六的家");
		user.getAddressSet().add("赵六的窝");
		save(user);
	}
	
	@Test
	public void testGet(){
		User user = get(4);
		System.out.println(user);
	}
}
