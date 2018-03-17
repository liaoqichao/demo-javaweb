package hibernate.demo14.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo14.domain.IdCard;
import hibernate.demo14.domain.Person;
import lqcUtils.HibernateUtils;

public class PersonDao {

	/**
	 * ���棬һ��һ������ϵ
	 * �����������ά��������ϵ�����������Բ��ý���������ϵ
	 */
//	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			
			Person person = new Person();
			person.setName("����");
			
			IdCard idCard = new IdCard();
			idCard.setNumber("232323X");
			
			//��������
//			person.setIdCard(idCard);	//����ע�͵������������Ϊ������ά��������ϵ
			idCard.setPerson(person);	//������������Ҳע�͵���
			
			
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
			
			//----���Ĵ���------
			
			//��ȡһ������ʾ��һ����Ϣ
			Person person = session.get(Person.class, 2);
			System.out.println(person.getIdCard());
			
			//��Ϊ˫����������ԶԷ�Ҳһ��
			
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
	 * ���������ϵ��inverse����ʵ�ڼ��ϱ�ǩ�����һ�����ԣ�����û�У�����д���ˡ�
	 * ֻ�����ù�������Ϊ�ա��Զ�������������Ϊnull��ֻ�д���������ſ��Խ����ϵ��������������Խ����ϵ��
	 */
//	@Test
	public void testRemoveRelation(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			
			//���������ϵ
			//��������������ϵ�����Կ��ԣ���Ϊ������������Ϊ�գ����ᱨ��ֻ�ǲ��ܽ����ϵ
			IdCard idCard = session.get(IdCard.class, 2);
			idCard.setPerson(null);
			
			//��������������ϵ�����ԣ����ᱨ��ֻ�ǲ��ܽ����ϵ
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
	 * ɾ���й�����ϵ�Ķ��󣬺ͻ�������ķ�ʽһ��
	 */
	@Test
	public void testDelete(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------

			IdCard idCard = session.get(IdCard.class, 4);
			session.delete(idCard);//һ��һ��ϵ��������������������������Լ������ԣ����ܲ���ά����ϵû��ϵ���ɹ�
			
//			Person person = session.get(Person.class, 4);
//			session.delete(person);//һ��һ��ϵ�������������������ά��������ϵ������ִ��delete�ᱨ��Υ�����Լ��
			
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
