package hibernate.demo23.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestDao {

	@Test
	public void test() throws Exception{
		/*
		 * �����ڴ���sessionFactory��ʱ�򴴽��ġ�����sessionFactory��������ݿ����ж�Ӧ�ı�˵��ӳ��ɹ�
		 */
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
	}
}
