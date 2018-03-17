package hibernate.demo10.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo10.domain.Student;
import hibernate.demo10.domain.Teacher;
import lqcUtils.HibernateUtils;

public class DaoTest {

	/**
	 * 添加
	 */
//	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			Student student1 = new Student();
			student1.setName("喵同学");
			Student student2 = new Student();
			student2.setName("汪同学");
			
			Teacher teacher1 = new Teacher();
			teacher1.setName("哞老师");
			Teacher teacher2 = new Teacher();
			teacher2.setName("咩老师");
			
			//关联起来
			student1.getTeacherSet().add(teacher1);
			student1.getTeacherSet().add(teacher2);
			
			student2.getTeacherSet().add(teacher1);
			student2.getTeacherSet().add(teacher2);

			teacher1.getStudentSet().add(student1);
			teacher1.getStudentSet().add(student2);
			teacher2.getStudentSet().add(student1);
			teacher2.getStudentSet().add(student2);
			
			/*
			 * 但是这样中间表会主键都会重复一遍。因为两边都会维护关联关系，所以把一边的注释掉就可以。
			 * 虽然注释一边就可以，但是从对象的角度应该两边都设置，而且还不报错。所以对多对多关系，配置文件中要有一方设置
			 * inverse="true"，表示它不管理关系。一般根据业务情况，选择谁来维护。
			 */
			/*
			 * 一对多中的维护关联关系是指修改外键的值。
			 * 多对多中的维护关联关系是值增加或删除中间表中的记录。
			 */
			 
			session.save(student1);
			session.save(student2);
			session.save(teacher1);
			session.save(teacher2);
			
			
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
	 * 获取
	 */
	public void testGet(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			//获取一方，显示另一方
			Teacher teacher = session.get(Teacher.class, 3L);//3L表示Long型数字3，不区分大小写
			System.out.println(teacher);
			System.out.println(teacher.getStudentSet());

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
	 * 解除关系
	 */
	public void testRemoveRelation(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			Teacher teacher = session.get(Teacher.class, 3L);//3L表示Long型数字3，不区分大小写
			teacher.getStudentSet().clear();//清空学生
			/*
			 * 然而inverse=true所以没效果，把teacher的inverse=false，同时把student的inverse改成true就可以。
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
	
	/**
	 * 删除有关联关系的对象
	 */
	@Test
	public void testDelete(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----核心代码------
			/*
			 * 删除一方：
			 * a, 如果没有关联关系，能删除
			 * b, 如果有关联关系，且inverse=true，不能删除。因为它不维护关联关系。
			 * c, 如果有关联关系，且inverse=false，可以删除，因为它维护关联关系。
			 */
			//inverse = false，维护关联关系，删除成功.先先出关联关系再删除实体表中的记录
			Teacher teacher = session.get(Teacher.class, 4L);
			session.delete(teacher);
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
