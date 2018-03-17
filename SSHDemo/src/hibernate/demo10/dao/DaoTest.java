package hibernate.demo10.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo10.domain.Student;
import hibernate.demo10.domain.Teacher;
import lqcUtils.HibernateUtils;

public class DaoTest {

	/**
	 * ���
	 */
//	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			Student student1 = new Student();
			student1.setName("��ͬѧ");
			Student student2 = new Student();
			student2.setName("��ͬѧ");
			
			Teacher teacher1 = new Teacher();
			teacher1.setName("����ʦ");
			Teacher teacher2 = new Teacher();
			teacher2.setName("����ʦ");
			
			//��������
			student1.getTeacherSet().add(teacher1);
			student1.getTeacherSet().add(teacher2);
			
			student2.getTeacherSet().add(teacher1);
			student2.getTeacherSet().add(teacher2);

			teacher1.getStudentSet().add(student1);
			teacher1.getStudentSet().add(student2);
			teacher2.getStudentSet().add(student1);
			teacher2.getStudentSet().add(student2);
			
			/*
			 * ���������м������������ظ�һ�顣��Ϊ���߶���ά��������ϵ�����԰�һ�ߵ�ע�͵��Ϳ��ԡ�
			 * ��Ȼע��һ�߾Ϳ��ԣ����ǴӶ���ĽǶ�Ӧ�����߶����ã����һ����������ԶԶ�Զ��ϵ�������ļ���Ҫ��һ������
			 * inverse="true"����ʾ���������ϵ��һ�����ҵ�������ѡ��˭��ά����
			 */
			/*
			 * һ�Զ��е�ά��������ϵ��ָ�޸������ֵ��
			 * ��Զ��е�ά��������ϵ��ֵ���ӻ�ɾ���м���еļ�¼��
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
	 * ��ȡ
	 */
	public void testGet(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			//��ȡһ������ʾ��һ��
			Teacher teacher = session.get(Teacher.class, 3L);//3L��ʾLong������3�������ִ�Сд
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
	 * �����ϵ
	 */
	public void testRemoveRelation(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			Teacher teacher = session.get(Teacher.class, 3L);//3L��ʾLong������3�������ִ�Сд
			teacher.getStudentSet().clear();//���ѧ��
			/*
			 * Ȼ��inverse=true����ûЧ������teacher��inverse=false��ͬʱ��student��inverse�ĳ�true�Ϳ��ԡ�
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
	 * ɾ���й�����ϵ�Ķ���
	 */
	@Test
	public void testDelete(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			/*
			 * ɾ��һ����
			 * a, ���û�й�����ϵ����ɾ��
			 * b, ����й�����ϵ����inverse=true������ɾ������Ϊ����ά��������ϵ��
			 * c, ����й�����ϵ����inverse=false������ɾ������Ϊ��ά��������ϵ��
			 */
			//inverse = false��ά��������ϵ��ɾ���ɹ�.���ȳ�������ϵ��ɾ��ʵ����еļ�¼
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
