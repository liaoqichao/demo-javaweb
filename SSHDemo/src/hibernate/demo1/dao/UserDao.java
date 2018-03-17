package hibernate.demo1.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import hibernate.demo1.domain.User;

public class UserDao {

//	1.�õ�sessionFactory
	private static SessionFactory sessionFactory;
	static{
		Configuration cfg = new Configuration();//ͨ�����������ȡ�������ļ�
		cfg.configure("hibernate.cfg.xml");//��ȡ�������ļ�
		sessionFactory = cfg.buildSessionFactory();//������������session����
	}
	@Test
	public void testSave() throws Exception {
		
		User user = new User();
		user.setName("����");
		
		//ʹ��hibernate����
		//2.�õ�session
		Session session = sessionFactory.openSession();//session�õ���֮����
		//3.����ʼ
		Transaction tx = session.beginTransaction();
		
		session.save(user);
		tx.commit();//�ύ����
		
		//finally����ع������ͷ���Դ��
		session.close();//��˱������Դ��Ҫ��ϧ���ͷ���Դ������һ���ǹرգ�������������ӳؾͻ�黹����
		
	}
	@Test
	public void testget() throws Exception {
		//ʹ��hibernate��ȡ
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User)session.get(User.class, 1);//��һ�������Ǳ��������Ǳ��ʵ���Ӧ�ˣ�����дʵ������͡��ڶ�������������
		tx.commit();
		session.close();
		System.out.println(user);
	}
}
