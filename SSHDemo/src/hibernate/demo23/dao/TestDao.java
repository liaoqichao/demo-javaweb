package hibernate.demo23.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestDao {

	@Test
	public void test() throws Exception{
		/*
		 * 表是在创建sessionFactory的时候创建的。创建sessionFactory后，如果数据库中有对应的表，说明映射成功
		 */
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
	}
}
