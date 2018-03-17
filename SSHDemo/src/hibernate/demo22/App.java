package hibernate.demo22;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import lqcUtils.HibernateUtils;

/**
 * sessionFactory.getCurrentSession()
 * 获取当前线程的session。主配置文件需要配置
 * <property name="current_session_context_class">thread</property>
 */
public class App {

	/**
	 * openSession永远是打开新的session。不会绑定到指定上下文和查找上下文。
	 * getCurrentSession是去指定的上下文找绑定的session对象如果有就返回，如果没有就创建一个新session，并绑定到上下文然后返回。
	 */
	
	private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	
//	@Test
	public void testSession(){
		
		Session session4 = HibernateUtils.openSession();
		
		Session session1 = sessionFactory.getCurrentSession();
		Session session2 = sessionFactory.getCurrentSession();
		
		Session session3 = HibernateUtils.openSession();
		
		//getCurrentSession2次
		System.out.println((session1 != null)&&(session1 == session2));//true
		
		//先getCurrent，后opent
		System.out.println(session3 == session1);//false;
		
		//先open，后get
		System.out.println(session4 == session1);//false
		
	}
	/**
	 * 使用getCurrentSession之后就不用session.close了。
	 */
	@Test
	public void testSessionClose(){
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		System.out.println("使用session了");
		session.getTransaction().commit();
//		session.close();//报错，session已经关闭
	}
}
