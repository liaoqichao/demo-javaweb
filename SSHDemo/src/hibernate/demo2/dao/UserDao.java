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
	 * 增
	 * 
	 * @param user
	 */
	public void save(User user) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);// runtime异常，不能被捕获
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();// 回滚
			throw e;// 没有处理异常就一定要扔出，不然业务层不知道dao层出现了异常。
		} finally {
			session.close();
		}
	}

	/**
	 * 删
	 */
	public void delete(int id) {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		// 2.声明事务对象
		Transaction tx = null;
		try {
			// 3.开启事务
			tx = session.beginTransaction();
			// 4.核心代码
			User user = session.get(User.class, id);// 根据主键获取对象
			session.delete(user);// 删除的是实体对象
			// 5.提交事务
			tx.commit();
		} catch (Exception e) {
			// 6.回滚事务
			tx.rollback();
		} finally {
			// 7.释放session
			session.close();
		}
	}

	/**
	 * 改
	 * 
	 * @param user
	 */
	public void update(User user) {

		// 1.得到session
		Session session = HibernateUtils.openSession();
		// 2.声明事务对象
		Transaction tx = null;
		try {
			// 3.开启事务
			tx = session.beginTransaction();
			// 4.核心代码
			session.update(user);
			// 5.提交事务
			tx.commit();
		} catch (Exception e) {
			// 6.回滚事务
			tx.rollback();
		} finally {
			// 7.释放session
			session.close();
		}
	}

	/**
	 * 按主键查询
	 * 
	 * @param user
	 * @return
	 */
	public User getById(int id) {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		// 2.声明事务对象
		Transaction tx = null;
		try {
			// 3.开启事务
			tx = session.beginTransaction();
			// 4.核心代码
			User user = session.get(User.class, id);// 表映射的对象和表的主键
			// 5.提交事务
			tx.commit();
			return user;
		} catch (Exception e) {
			// 6.回滚事务
			tx.rollback();
		} finally {
			// 7.释放session
			session.close();
		}
		return null;
	}

	/**
	 * 查询所有用户：使用HQL（Hibernate Query Language）语句
	 */
	public List<User> findAll() {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		// 2.声明事务对象
		Transaction tx = null;
		try {
			// 3.开启事务
			tx = session.beginTransaction();
			// 4.核心代码
			// 查询提交可以通过SQL语句，但是不推荐，因为可能会换数据库，方言不一样
			/*
			 * 使用HQL语句查询所有 SQL查询表和表中的字段 不区分大小写 HQL（hibernate query
			 * language）查询的是对象和对象的属性 关键字不区分大小写，但是类名和属性名区分大小写。 这里的FROM user ==
			 * SELECT * FROM User ==SQL中的 SELECT * FROM t_user
			 */
			@SuppressWarnings("unchecked")
			List<User> userList = session.createQuery("FROM User").list();
			// 5.提交事务
			tx.commit();
			return userList;
		} catch (Exception e) {
			// 6.回滚事务
			tx.rollback();
		} finally {
			// 7.释放session
			session.close();
		}
		return null;
	}

	/**
	 * 分页查询所有（数据库只查一点，用户只显示一点） pageBean呢?
	 * @param firstResult当前页的第一条记录的下标
	 * @return 一页的数据列表
	 */
	public QueryResult<User> findAll(int firstResult, int maxResults) {
		// 1.得到session
		Session session = HibernateUtils.openSession();
		// 2.声明transaction
		Transaction tx = null;
		try {
			// 3.开启事务
			tx = session.beginTransaction();
			// 4.核心代码
			//使用HQL语句查询一页的数据列表
			@SuppressWarnings("unchecked")
//			Query query = session.createQuery("FROM User");
//			query.setFirstResult(firstResult);//
//			query.setMaxResults(maxResults);//
//			List<User> userList = query.list(); 
			
			//通常4行连着写
			//查询当前页的记录
			//方式一：用Query对象+HQL（重点）
			List<User> userList = session.createQuery("FROM User")//
					.setFirstResult(firstResult)//
					.setMaxResults(maxResults)//
					.list();
			//方式二：用Criteria对象
//			Criteria criteria = session.createCriteria(User.class);//有过滤的条件吗没有，不加。有排序条件吗?没有，不加。
//			criteria.add(Restrictions.eq("id", 5));//相当于WHERE id=5。gt是大于，大于等于是ge，lt小于，le小于等于，之间：between
//			criteria.addOrder(Order.asc("id"));//相当于ORDER BY id ASC; 还有降序是DESC
//			List<User> userList = criteria.list();
			//查询总记录数
			Long count = (Long)session.createQuery(//
					"SELECT COUNT(*) FROM User")//
					.uniqueResult();//查询结果只有一条记录时
			// 5.提交
			tx.commit();
			return new QueryResult<>(count.intValue(), firstResult, maxResults, userList);
		} catch (Exception e) {
			// 6.回滚
			tx.rollback();
			throw e;
		} finally {
			//7.释放资源
			session.close();
		}
	}

}
