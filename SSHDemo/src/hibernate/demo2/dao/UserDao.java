package hibernate.demo2.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import hibernate.demo2.domain.QueryResult;
import hibernate.demo2.domain.User;

public class UserDao {

	/**
	 * ��
	 * 
	 * @param user
	 */
	public void save(User user) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);// runtime�쳣�����ܱ�����
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();// �ع�
			throw e;// û�д����쳣��һ��Ҫ�ӳ�����Ȼҵ��㲻֪��dao��������쳣��
		} finally {
			session.close();
		}
	}

	/**
	 * ɾ
	 */
	public void delete(int id) {
		// 1.�õ�session
		Session session = HibernateUtils.openSession();
		// 2.�����������
		Transaction tx = null;
		try {
			// 3.��������
			tx = session.beginTransaction();
			// 4.���Ĵ���
			User user = session.get(User.class, id);// ����������ȡ����
			session.delete(user);// ɾ������ʵ�����
			// 5.�ύ����
			tx.commit();
		} catch (Exception e) {
			// 6.�ع�����
			tx.rollback();
		} finally {
			// 7.�ͷ�session
			session.close();
		}
	}

	/**
	 * ��
	 * 
	 * @param user
	 */
	public void update(User user) {

		// 1.�õ�session
		Session session = HibernateUtils.openSession();
		// 2.�����������
		Transaction tx = null;
		try {
			// 3.��������
			tx = session.beginTransaction();
			// 4.���Ĵ���
			session.update(user);
			// 5.�ύ����
			tx.commit();
		} catch (Exception e) {
			// 6.�ع�����
			tx.rollback();
		} finally {
			// 7.�ͷ�session
			session.close();
		}
	}

	/**
	 * ��������ѯ
	 * 
	 * @param user
	 * @return
	 */
	public User getById(int id) {
		// 1.�õ�session
		Session session = HibernateUtils.openSession();
		// 2.�����������
		Transaction tx = null;
		try {
			// 3.��������
			tx = session.beginTransaction();
			// 4.���Ĵ���
			User user = session.get(User.class, id);// ��ӳ��Ķ���ͱ������
			// 5.�ύ����
			tx.commit();
			return user;
		} catch (Exception e) {
			// 6.�ع�����
			tx.rollback();
		} finally {
			// 7.�ͷ�session
			session.close();
		}
		return null;
	}

	/**
	 * ��ѯ�����û���ʹ��HQL��Hibernate Query Language�����
	 */
	public List<User> findAll() {
		// 1.�õ�session
		Session session = HibernateUtils.openSession();
		// 2.�����������
		Transaction tx = null;
		try {
			// 3.��������
			tx = session.beginTransaction();
			// 4.���Ĵ���
			// ��ѯ�ύ����ͨ��SQL��䣬���ǲ��Ƽ�����Ϊ���ܻỻ���ݿ⣬���Բ�һ��
			/*
			 * ʹ��HQL����ѯ���� SQL��ѯ��ͱ��е��ֶ� �����ִ�Сд HQL��hibernate query
			 * language����ѯ���Ƕ���Ͷ�������� �ؼ��ֲ����ִ�Сд���������������������ִ�Сд�� �����FROM user ==
			 * SELECT * FROM User ==SQL�е� SELECT * FROM t_user
			 */
			@SuppressWarnings("unchecked")
			List<User> userList = session.createQuery("FROM User").list();
			// 5.�ύ����
			tx.commit();
			return userList;
		} catch (Exception e) {
			// 6.�ع�����
			tx.rollback();
		} finally {
			// 7.�ͷ�session
			session.close();
		}
		return null;
	}

	/**
	 * ��ҳ��ѯ���У����ݿ�ֻ��һ�㣬�û�ֻ��ʾһ�㣩 pageBean��?
	 * @param firstResult��ǰҳ�ĵ�һ����¼���±�
	 * @return һҳ�������б�
	 */
	public QueryResult<User> findAll(int firstResult, int maxResults) {
		// 1.�õ�session
		Session session = HibernateUtils.openSession();
		// 2.����transaction
		Transaction tx = null;
		try {
			// 3.��������
			tx = session.beginTransaction();
			// 4.���Ĵ���
			//ʹ��HQL����ѯһҳ�������б�
			@SuppressWarnings("unchecked")
//			Query query = session.createQuery("FROM User");
//			query.setFirstResult(firstResult);//
//			query.setMaxResults(maxResults);//
//			List<User> userList = query.list(); 
			
			//ͨ��4������д
			//��ѯ��ǰҳ�ļ�¼
			//��ʽһ����Query����+HQL���ص㣩
			List<User> userList = session.createQuery("FROM User")//
					.setFirstResult(firstResult)//
					.setMaxResults(maxResults)//
					.list();
			//��ʽ������Criteria����
//			Criteria criteria = session.createCriteria(User.class);//�й��˵�������û�У����ӡ�������������?û�У����ӡ�
//			criteria.add(Restrictions.eq("id", 5));//�൱��WHERE id=5��gt�Ǵ��ڣ����ڵ�����ge��ltС�ڣ�leС�ڵ��ڣ�֮�䣺between
//			criteria.addOrder(Order.asc("id"));//�൱��ORDER BY id ASC; ���н�����DESC
//			List<User> userList = criteria.list();
			//��ѯ�ܼ�¼��
			Long count = (Long)session.createQuery(//
					"SELECT COUNT(*) FROM User")//
					.uniqueResult();//��ѯ���ֻ��һ����¼ʱ
			// 5.�ύ
			tx.commit();
			return new QueryResult<>(count.intValue(), firstResult, maxResults, userList);
		} catch (Exception e) {
			// 6.�ع�
			tx.rollback();
			throw e;
		} finally {
			//7.�ͷ���Դ
			session.close();
		}
	}

}
