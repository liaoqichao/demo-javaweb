package hibernate.demo1.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import hibernate.demo1.domain.User;

public class UserDao {

//	1.得到sessionFactory
	private static SessionFactory sessionFactory;
	static{
		Configuration cfg = new Configuration();//通过这个类来读取主配置文件
		cfg.configure("hibernate.cfg.xml");//读取主配置文件
		sessionFactory = cfg.buildSessionFactory();//根据配置生成session工厂
	}
	@Test
	public void testSave() throws Exception {
		
		User user = new User();
		user.setName("张三");
		
		//使用hibernate保存
		//2.得到session
		Session session = sessionFactory.openSession();//session得到来之不易
		//3.事务开始
		Transaction tx = session.beginTransaction();
		
		session.save(user);
		tx.commit();//提交事务
		
		//finally里面回滚事务，释放资源。
		session.close();//如此宝贵的资源需要珍惜。释放资源，但不一定是关闭，如果配置了连接池就会归还给池
		
	}
	@Test
	public void testget() throws Exception {
		//使用hibernate获取
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User)session.get(User.class, 1);//第一个参数是表名，但是表和实体对应了，所以写实体的类型。第二个参数是主键
		tx.commit();
		session.close();
		System.out.println(user);
	}
}
