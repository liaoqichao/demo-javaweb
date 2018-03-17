package hibernate.demo2.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static SessionFactory sessionFactory;
	static{
//		Configuration cfg = new Configuration();
//		cfg.configure();//Ĭ�϶�ȡ/src/hibernate.cfg.xml
//		cfg.configure("hibernate.cfg.xml");//��ȡָ��λ�õ������ļ�
//		sessionFactory = cfg.buildSessionFactory();
		//3�п�����һ�д���,һ��һ������������Ŀ�ע���Ƿ�ֹ��ʽ�������ʱ���ȥ
		sessionFactory = new Configuration()//
				.configure()//
				.buildSessionFactory();
	}
	
	/**
	 * ��ȡȫ��Ψһ��sessionFactory��sessionFactory���̰߳�ȫ�ģ������ڶ��߳���ʹ�á�
	 * @return
	 */
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	/**
	 * ��ȫ��Ψһ��SessionFactory�п���һ��session
	 */
	public static Session openSession(){
		return sessionFactory.openSession();
	}
}
