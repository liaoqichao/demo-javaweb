package update.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import update.dao.ClientBeanDao;
import update.domain.ClientBean;

@Transactional
public class ClientBeanDaoImpl implements ClientBeanDao {

	@Resource
	private SessionFactory sessionFactory;

	public void save(ClientBean clientBean) {
		Session session = sessionFactory.getCurrentSession();
		session.save(clientBean);
	}

	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		ClientBean clientBean = session.get(ClientBean.class, id);
		session.delete(clientBean);
	}

	public void update(ClientBean clientBean) {
		Session session = sessionFactory.getCurrentSession();
		session.update(clientBean);
	}

	public ClientBean get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(ClientBean.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ClientBean> getList() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ClientBean";
		return session.createQuery(hql)//
				.setCacheable(true)// 使用查询缓存
				.list();
	}

	public ClientBean findByClientname(String clientname) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ClientBean WHERE clientname=:clientname";
		return (ClientBean) session.createQuery(hql)//
				.setParameter("clientname", clientname)//
				.uniqueResult();
	}

}
