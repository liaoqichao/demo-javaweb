package hibernate.demo2.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static SessionFactory sessionFactory;
	static{
//		Configuration cfg = new Configuration();
//		cfg.configure();//默认读取/src/hibernate.cfg.xml
//		cfg.configure("hibernate.cfg.xml");//读取指定位置的配置文件
//		sessionFactory = cfg.buildSessionFactory();
		//3行可以用一行代替,一行一个方法，后面的空注释是防止格式化代码的时候回去
		sessionFactory = new Configuration()//
				.configure()//
				.buildSessionFactory();
	}
	
	/**
	 * 获取全局唯一的sessionFactory，sessionFactory是线程安全的，可以在多线程下使用。
	 * @return
	 */
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	/**
	 * 从全局唯一的SessionFactory中开启一个session
	 */
	public static Session openSession(){
		return sessionFactory.openSession();
	}
}
