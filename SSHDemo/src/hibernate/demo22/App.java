package hibernate.demo22;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import lqcUtils.HibernateUtils;

/**
 * sessionFactory.getCurrentSession()
 * ��ȡ��ǰ�̵߳�session���������ļ���Ҫ����
 * <property name="current_session_context_class">thread</property>
 */
public class App {

	/**
	 * openSession��Զ�Ǵ��µ�session������󶨵�ָ�������ĺͲ��������ġ�
	 * getCurrentSession��ȥָ�����������Ұ󶨵�session��������оͷ��أ����û�оʹ���һ����session�����󶨵�������Ȼ�󷵻ء�
	 */
	
	private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	
//	@Test
	public void testSession(){
		
		Session session4 = HibernateUtils.openSession();
		
		Session session1 = sessionFactory.getCurrentSession();
		Session session2 = sessionFactory.getCurrentSession();
		
		Session session3 = HibernateUtils.openSession();
		
		//getCurrentSession2��
		System.out.println((session1 != null)&&(session1 == session2));//true
		
		//��getCurrent����opent
		System.out.println(session3 == session1);//false;
		
		//��open����get
		System.out.println(session4 == session1);//false
		
	}
	/**
	 * ʹ��getCurrentSession֮��Ͳ���session.close�ˡ�
	 */
	@Test
	public void testSessionClose(){
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		System.out.println("ʹ��session��");
		session.getTransaction().commit();
//		session.close();//����session�Ѿ��ر�
	}
}
