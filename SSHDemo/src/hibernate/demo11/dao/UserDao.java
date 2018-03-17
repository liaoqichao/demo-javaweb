package hibernate.demo11.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo11.domain.User;
import lqcUtils.HibernateUtils;

public class UserDao {

	/**
	 * session.save()把临时状态变成持久化状态
	 */
//	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		User user = null ;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			user = new User();	//临时状态
			user.setName("张三");
			session.save(user);		//持久化状态
			
			//save同一个对象多次，只执行一次SQL语句
			
			user.setName("李四");  	//数据库会把name改成李四

			//---------------
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session!= null ) session.close();
		}
		
		user.setName("王五");					//游离状态，数据库还是李四
		System.out.println(user.getName());	//游离状态，王五
	}
	
	/**
	 * 把持久化状态或者游离状态的对象转为删除状态的对象
	 */
//	@Test
	public void testDelete(){
		Session session = null;
		Transaction tx = null;
		User user  = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			user = session.get(User.class, 2);//持久化状态
			session.delete(user);//调用flush的时候执行，参数不能为空值
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
	 * 把游离状态变成持久化状态
	 */
//	@Test
	public void testUpdate(){
		Session session = null;
		Transaction tx = null;
		User user = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			
			user = session.get(User.class, 2);//持久化状态
			/*
			 * session有个集合来存储持久化对象。集合都有clear()方法，session的clear方法就是清空这个集合，
			 * 这样session就不再管理这些对象。
			 * 有缓冲区的对象都有flush方法。io流中很多多有。
			 */
			System.out.println(user.getName());

			session.flush();	//把数据刷新到数据库，不然会在tx.commit的时候才执行SQL语句，这里要强制现在就执行SQL语句
			session.evict(user);//删除session管理的指定对象，把一个指定对象变成游离状态
			session.clear();	//user变成游离状态，把所有对象变成游离状态
			user.setName("巴拉巴拉小魔仙");	//数据库不会发生改变。
			
			session.update(user); //游离状态转为持久化状态
			//---------------
			tx.commit();
			
			System.out.println(user.getName());
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session!= null ) session.close();
		}
	}
	
	/**
	 * 获取持久化对象
	 */
//	@Test
	public void testGet(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			User user = session.get(User.class, 2);//持久化对象，这里为null，数据库只有主键为3的记录
			System.out.println(user);//这个对象会马上执行，而不是session.flush的时候执行。不存在返回null

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
	 * 获取持久化对象.
	 * load和get的区别是get是立刻执行SQL语句，load是要用查询得到的对象的时候才执行。
	 * load方法的获取查询结果的类型User.class和获取主键是不会查询数据库，而是直接在load(XX.class, 5);参数中得到。
	 * load...懒加载提高效率用
	 */
//	@Test
	public void testLoad(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			User user = session.load(User.class, 3);
			/*
			 * > load不会马上执行SQL语句，而是第一次使用非id或class的时候执行SQL，这样可以提高效率。
			 * > get查询不到结果会返回null，load查询不到结果会抛出异常。
			 * > load返回的是代理对象，get返回的是真实对象。
			 * 		代理对象是实体类的子类，如果把实体类加上final关键字，就不能有子类，但是load不会报错，只是懒加载失效。
			 * > 让懒加载失效的方式：
			 * 	1.实体类加上final关键字
			 * 	2.hbm.xml中<class ... lazy="false">
			 * > 如果大量查询，插入，...，等让session中的集合塞满，就出现内存溢出。例如插入100W条记录。
			 * 	 可以每插入100个，执行session.clear方法一次。这样解决内存溢出问题。
			 */
			System.out.println(user.getClass());//class hibernate.demo11.domain.User_$$_jvst348_0
			
			System.out.println(user);
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
	 * 把对象从临时状态或者游离状态变成持久化状态
	 */
//	@Test
	public void testSaveOrUpdate(){
		Session session = null;
		Transaction tx = null;
		User user = null;
		User u = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			user = new User();
			user.setName("test");
			session.saveOrUpdate(user);

			/*
			 * 本方法是根据id来判断对象是什么状态的，如果id是原始值（对象是null，数字类型是0，Integer是null，int是0）
			 * 就是临时状态，如果不是就是游离状态。
			 * session.saveOrUpdate(new User(300));这里的user就是游离状态。不管数据库是否存在，
			 * 不会查询数据库。 
			 */
			u = session.get(User.class, 2);//持久化对象
			session.evict(u);//u变成游离对象
			u.setName("张三");
			session.saveOrUpdate(u);//变成持久化对象
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
	 * 操作大量数据，要防止session中的对象过多，而内存溢出。
	 */
	@Test
	public void testBatchSave(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			//往数据库中存了86个就内存溢出异常
			for(int i = 0 ; i<100 ; i++){
				User user = new User();
				user.setName("test_"+i);
				session.save(user);//
				if(i%20 == 0){
					session.flush();//把内存的数据刷新到数据库
					session.clear();//清空session中存储对象的集合(数据结构)
				}
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
}
