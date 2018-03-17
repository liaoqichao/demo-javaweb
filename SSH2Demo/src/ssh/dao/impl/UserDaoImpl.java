package ssh.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import ssh.dao.UserDao;
import ssh.domain.User;

@Transactional
public class UserDaoImpl implements UserDao {

	@Resource
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see update.dao.impl.UserDao#save(update.domain.User)
	 */
	public void save(User user){
		sessionFactory.getCurrentSession().save(user);
	}
}
