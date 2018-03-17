package update.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import update.dao.UserDao;
import update.domain.User;

@Transactional
public class UserDaoImpl implements UserDao {

	@Resource
	private SessionFactory sessionFactory;
	
	public void save(User user){
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
	public void delete(Integer id){
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		if(user != null){
			session.delete(user);
		}
	}
	
	public void update(User user){
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}
	
	public User get(Integer id){
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> list(){
		Session session = sessionFactory.getCurrentSession();
		String hql ="FROM User";
		return session.createQuery(hql)//
				.setCacheable(true)//
				.list();
	}
	
	public User findByUsername(String username){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE username=:username";
		return (User) session.createQuery(hql)//
				.setParameter("username", username)//
				.uniqueResult();
	}
}
