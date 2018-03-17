package hibernate.demo17.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo17.domain.Article;
import hibernate.demo17.domain.Reply;
import hibernate.demo17.domain.Topic;
import lqcUtils.HibernateUtils;

/**
 * 用hilo，报错。
 * 因为只是有继承关系，没有关联关系，所以只用测试保存和获取.
 */
public class ArticleDao {

	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			//核心代码
			
//			Article acticle = new Article();
//			acticle.setTitle("这是一个article");
			
			Topic topic = new Topic();
			topic.setTitle("这是一个topic");
			Reply reply = new Reply();
			reply.setTitle("这是一个reply2");
			
//			session.save(acticle);
			session.save(topic);
			session.save(reply);
			
			//
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session != null)
				session.close();
		}
	}
	
	/**
	 * 使用每个类一张表的方式。
	 */
	@Test
	public void testGet(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			//核心代码
			
			//如果article的abstract=true下面这句代码就会报错。只能用hilo
			Article article = session.get(Article.class, 1);//Article [id=1, title=这是一个topic, content=null, postTime=null]
			
			Topic topic = session.get(Topic.class, 2);//null，因为是Reply类型的
			Reply reply = session.get(Reply.class, 3);//Article [id=3, title=这是一个reply2, content=null, postTime=null]
			
			/*
			 * 只有父类型能接收值。
			 */
			System.out.println(article);//Article [id=1, title=这是一个topic, content=null, postTime=null]
			System.out.println(topic);//null
			System.out.println(reply);//null
			
			
			//父类类型获取子类类型的记录也可以。
			Article article1 = session.get(Article.class, 1);
			Article article2 = session.get(Article.class, 2);
			System.out.println(article1);//Article [id=1, title=这是一个topic, content=null, postTime=null]
			System.out.println(article2);//Article [id=2, title=这是一个reply, content=null, postTime=null]
			
			//
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally{
			if(session != null)
				session.close();
		}
	}
}
