package hibernate.demo15.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo15.domain.Article;
import hibernate.demo15.domain.Reply;
import hibernate.demo15.domain.Topic;
import lqcUtils.HibernateUtils;

/**
 * 因为只是有继承关系，没有关联关系，所以只用测试保存和获取
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
			
			Article acticle = new Article();
			acticle.setTitle("这是一个article");
			
			Topic topic = new Topic();
			topic.setTitle("这是一个topic");
			Reply reply = new Reply();
			reply.setTitle("这是一个reply");
			
			session.save(acticle);
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
	
	@Test
	public void testGet(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			//核心代码
			
			Article article = session.get(Article.class, 1);
			Topic topic = session.get(Topic.class, 2);
			Reply reply = session.get(Reply.class, 3);
			
			System.out.println(article);
			System.out.println(topic);
			System.out.println(reply);
			
			
			//父类类型获取子类类型的记录也可以。
			Article article1 = session.get(Article.class, 2);
			Article article2 = session.get(Article.class, 3);
			System.out.println(article1);//Article [id=2, title=这是一个topic, content=null, postTime=null]
			System.out.println(article2);//Article [id=3, title=这是一个reply, content=null, postTime=null]
			
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
