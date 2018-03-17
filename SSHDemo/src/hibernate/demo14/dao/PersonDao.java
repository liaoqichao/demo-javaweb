package hibernate.demo14.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo14.domain.IdCard;
import hibernate.demo14.domain.Person;
import lqcUtils.HibernateUtils;

public class PersonDao {

	/**
	 * 保存，一对一关联关系
	 * 无外键方不能维护关联关系，所以它可以不用建立关联关系
	 */
//	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			
			Person person = new Person();
			person.setName("王五");
			
			IdCard idCard = new IdCard();
			idCard.setNumber("232323X");
			
			//关联起来
//			person.setIdCard(idCard);	//可以注释掉无外键方，因为它不能维护关联关系
			idCard.setPerson(person);	//如果把有外键方也注释掉，
			
			
			session.save(person);
			session.save(idCard);
			

			//---------------
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session!= null ) session.close();
		}
	}

//	@Test
	public void testGet(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			
			//获取一方，显示另一方信息
			Person person = session.get(Person.class, 2);
			System.out.println(person.getIdCard());
			
			//因为双向关联，所以对方也一样
			
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
	 * 解除关联关系，inverse属性实在集合标签里面的一个属性，这里没有，所以写不了。
	 * 只能设置关联对象为空。自动将表的外键设置为null。只有从有外键方才可以解除关系，无外键方不可以解除关系。
	 */
//	@Test
	public void testRemoveRelation(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			
			//解除关联关系
			//从有外键方解除关系不可以可以，因为主键不能设置为空，不会报错，只是不能解除关系
			IdCard idCard = session.get(IdCard.class, 2);
			idCard.setPerson(null);
			
			//从无外键方解除关系不可以，不会报错只是不能解除关系
//			Person person = session.get(Person.class, 2);
//			person.setIdCard(null);
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
	 * 删除有关联关系的对象，和基于外键的方式一样
	 */
	@Test
	public void testDelete(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------

			IdCard idCard = session.get(IdCard.class, 4);
			session.delete(idCard);//一对一关系，它是有外键方，外键本身就是自己的属性，和能不能维护关系没关系，成功
			
//			Person person = session.get(Person.class, 4);
//			session.delete(person);//一对一关系，它是无外键方，不能维护关联关系，所以执行delete会报错，违反外键约束
			
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
