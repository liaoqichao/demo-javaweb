package hibernate.demo11.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo11.domain.User;
import lqcUtils.HibernateUtils;

public class UserDao {

	/**
	 * session.save()����ʱ״̬��ɳ־û�״̬
	 */
//	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		User user = null ;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			user = new User();	//��ʱ״̬
			user.setName("����");
			session.save(user);		//�־û�״̬
			
			//saveͬһ�������Σ�ִֻ��һ��SQL���
			
			user.setName("����");  	//���ݿ���name�ĳ�����

			//---------------
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session!= null ) session.close();
		}
		
		user.setName("����");					//����״̬�����ݿ⻹������
		System.out.println(user.getName());	//����״̬������
	}
	
	/**
	 * �ѳ־û�״̬��������״̬�Ķ���תΪɾ��״̬�Ķ���
	 */
//	@Test
	public void testDelete(){
		Session session = null;
		Transaction tx = null;
		User user  = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			user = session.get(User.class, 2);//�־û�״̬
			session.delete(user);//����flush��ʱ��ִ�У���������Ϊ��ֵ
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
	 * ������״̬��ɳ־û�״̬
	 */
//	@Test
	public void testUpdate(){
		Session session = null;
		Transaction tx = null;
		User user = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			
			user = session.get(User.class, 2);//�־û�״̬
			/*
			 * session�и��������洢�־û����󡣼��϶���clear()������session��clear�����������������ϣ�
			 * ����session�Ͳ��ٹ�����Щ����
			 * �л������Ķ�����flush������io���кܶ���С�
			 */
			System.out.println(user.getName());

			session.flush();	//������ˢ�µ����ݿ⣬��Ȼ����tx.commit��ʱ���ִ��SQL��䣬����Ҫǿ�����ھ�ִ��SQL���
			session.evict(user);//ɾ��session�����ָ�����󣬰�һ��ָ������������״̬
			session.clear();	//user�������״̬�������ж���������״̬
			user.setName("��������Сħ��");	//���ݿⲻ�ᷢ���ı䡣
			
			session.update(user); //����״̬תΪ�־û�״̬
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
	 * ��ȡ�־û�����
	 */
//	@Test
	public void testGet(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			User user = session.get(User.class, 2);//�־û���������Ϊnull�����ݿ�ֻ������Ϊ3�ļ�¼
			System.out.println(user);//������������ִ�У�������session.flush��ʱ��ִ�С������ڷ���null

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
	 * ��ȡ�־û�����.
	 * load��get��������get������ִ��SQL��䣬load��Ҫ�ò�ѯ�õ��Ķ����ʱ���ִ�С�
	 * load�����Ļ�ȡ��ѯ���������User.class�ͻ�ȡ�����ǲ����ѯ���ݿ⣬����ֱ����load(XX.class, 5);�����еõ���
	 * load...���������Ч����
	 */
//	@Test
	public void testLoad(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			User user = session.load(User.class, 3);
			/*
			 * > load��������ִ��SQL��䣬���ǵ�һ��ʹ�÷�id��class��ʱ��ִ��SQL�������������Ч�ʡ�
			 * > get��ѯ��������᷵��null��load��ѯ����������׳��쳣��
			 * > load���ص��Ǵ������get���ص�����ʵ����
			 * 		���������ʵ��������࣬�����ʵ�������final�ؼ��֣��Ͳ��������࣬����load���ᱨ��ֻ��������ʧЧ��
			 * > ��������ʧЧ�ķ�ʽ��
			 * 	1.ʵ�������final�ؼ���
			 * 	2.hbm.xml��<class ... lazy="false">
			 * > ���������ѯ�����룬...������session�еļ����������ͳ����ڴ�������������100W����¼��
			 * 	 ����ÿ����100����ִ��session.clear����һ�Ρ���������ڴ�������⡣
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
	 * �Ѷ������ʱ״̬��������״̬��ɳ־û�״̬
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
			
			//----���Ĵ���------
			user = new User();
			user.setName("test");
			session.saveOrUpdate(user);

			/*
			 * �������Ǹ���id���ж϶�����ʲô״̬�ģ����id��ԭʼֵ��������null������������0��Integer��null��int��0��
			 * ������ʱ״̬��������Ǿ�������״̬��
			 * session.saveOrUpdate(new User(300));�����user��������״̬���������ݿ��Ƿ���ڣ�
			 * �����ѯ���ݿ⡣ 
			 */
			u = session.get(User.class, 2);//�־û�����
			session.evict(u);//u����������
			u.setName("����");
			session.saveOrUpdate(u);//��ɳ־û�����
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
	 * �����������ݣ�Ҫ��ֹsession�еĶ�����࣬���ڴ������
	 */
	@Test
	public void testBatchSave(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			//�����ݿ��д���86�����ڴ�����쳣
			for(int i = 0 ; i<100 ; i++){
				User user = new User();
				user.setName("test_"+i);
				session.save(user);//
				if(i%20 == 0){
					session.flush();//���ڴ������ˢ�µ����ݿ�
					session.clear();//���session�д洢����ļ���(���ݽṹ)
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
