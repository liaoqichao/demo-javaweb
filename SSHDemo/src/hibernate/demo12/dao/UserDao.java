package hibernate.demo12.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo12.domain.User;
import lqcUtils.HibernateUtils;

/**
 * refresh(更新session缓存中的数据，使之与数据库一样)和修改事务隔离级别
 * @author Administrator
 *
 */
public class UserDao {
	/**
	 * 给数据库添加数据
	 */
//	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------

			for (int i = 0; i < 10; i++) {
				User user = new User();
				user.setName("test_"+i);
				session.save(user);
			}
			//---------------
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session!= null ) session.close();
		}
	}
	
	/**
	 * 演示refresh方法和修改事务的隔离级别
	 */
	@Test
	public void testGet(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			
			User user = session.get(User.class, 5);
			System.out.println(user.getName());//test_3
			
			session.refresh(user);	//去掉session的缓存中的user
			
			/*
			 * 这个时候手动修改数据库test_3的名字，然后再调用session.get方法，因为缓存没有，所以会取数据库中找。
			 * 但是因为手动修改数据库也是开启了事务。MySQL的默认事务隔离级别为可重复读（本事务开始的时候记录一个状态（快照），
			 * 别的事务如果在本事务开启后，提交前修改了数据，读的还是本事务开启时的数据。所以读到的是未修改的数据）
			 */
			//这里手动把数据库中的数据修改，该为55555
			user = session.get(User.class, 5);
			System.out.println(user.getName());
			/*
			 * 读到的还是test_3，不是55555，因为没有修改事务隔离级别到读未提交或读已提交(oracle默认事务隔离级别)。
			 * 可以在my.ini的[mysqld]中修改事务隔离级别，也可以在hibernate.cfg.xml中修改事务隔离级别
			 */
			//---------------
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session!= null ) session.close();
		}
	}

}
