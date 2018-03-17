package hibernate.demo17.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import hibernate.demo17.domain.Article;
import hibernate.demo17.domain.Reply;
import hibernate.demo17.domain.Topic;
import lqcUtils.HibernateUtils;

/**
 * ��hilo������
 * ��Ϊֻ���м̳й�ϵ��û�й�����ϵ������ֻ�ò��Ա���ͻ�ȡ.
 */
public class ArticleDao {

	@Test
	public void testSave(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			//���Ĵ���
			
//			Article acticle = new Article();
//			acticle.setTitle("����һ��article");
			
			Topic topic = new Topic();
			topic.setTitle("����һ��topic");
			Reply reply = new Reply();
			reply.setTitle("����һ��reply2");
			
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
	 * ʹ��ÿ����һ�ű�ķ�ʽ��
	 */
	@Test
	public void testGet(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.openSession();
			tx = session.beginTransaction();
			//���Ĵ���
			
			//���article��abstract=true����������ͻᱨ��ֻ����hilo
			Article article = session.get(Article.class, 1);//Article [id=1, title=����һ��topic, content=null, postTime=null]
			
			Topic topic = session.get(Topic.class, 2);//null����Ϊ��Reply���͵�
			Reply reply = session.get(Reply.class, 3);//Article [id=3, title=����һ��reply2, content=null, postTime=null]
			
			/*
			 * ֻ�и������ܽ���ֵ��
			 */
			System.out.println(article);//Article [id=1, title=����һ��topic, content=null, postTime=null]
			System.out.println(topic);//null
			System.out.println(reply);//null
			
			
			//�������ͻ�ȡ�������͵ļ�¼Ҳ���ԡ�
			Article article1 = session.get(Article.class, 1);
			Article article2 = session.get(Article.class, 2);
			System.out.println(article1);//Article [id=1, title=����һ��topic, content=null, postTime=null]
			System.out.println(article2);//Article [id=2, title=����һ��reply, content=null, postTime=null]
			
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
