package hibernate.demo12.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo12.domain.User;
import lqcUtils.HibernateUtils;

/**
 * refresh(����session�����е����ݣ�ʹ֮�����ݿ�һ��)���޸�������뼶��
 * @author Administrator
 *
 */
public class UserDao {
	/**
	 * �����ݿ��������
	 */
//	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------

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
	 * ��ʾrefresh�������޸�����ĸ��뼶��
	 */
	@Test
	public void testGet(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			
			//----���Ĵ���------
			
			User user = session.get(User.class, 5);
			System.out.println(user.getName());//test_3
			
			session.refresh(user);	//ȥ��session�Ļ����е�user
			
			/*
			 * ���ʱ���ֶ��޸����ݿ�test_3�����֣�Ȼ���ٵ���session.get��������Ϊ����û�У����Ի�ȡ���ݿ����ҡ�
			 * ������Ϊ�ֶ��޸����ݿ�Ҳ�ǿ���������MySQL��Ĭ��������뼶��Ϊ���ظ�����������ʼ��ʱ���¼һ��״̬�����գ���
			 * �����������ڱ����������ύǰ�޸������ݣ����Ļ��Ǳ�������ʱ�����ݡ����Զ�������δ�޸ĵ����ݣ�
			 */
			//�����ֶ������ݿ��е������޸ģ���Ϊ55555
			user = session.get(User.class, 5);
			System.out.println(user.getName());
			/*
			 * �����Ļ���test_3������55555����Ϊû���޸�������뼶�𵽶�δ�ύ������ύ(oracleĬ��������뼶��)��
			 * ������my.ini��[mysqld]���޸�������뼶��Ҳ������hibernate.cfg.xml���޸�������뼶��
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
