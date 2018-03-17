package update.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import update.dao.VersionDao;
import update.domain.Version;

@Transactional
public class VersionDaoImpl implements VersionDao {

	@Resource
	private SessionFactory sessionFactory;
	

	public void save(Version version){
		Session session = sessionFactory.getCurrentSession();
		session.save(version);
	}
	

	public void delete(Integer id){
		Session session = sessionFactory.getCurrentSession();
		Version version = get(id);
		session.delete(version);
	}
	

	public void update(Version version){
		Session session = sessionFactory.getCurrentSession();
		session.update(version);
	}
	

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Version get(Integer id){
		Session session = sessionFactory.getCurrentSession();
		return session.get(Version.class, id);
	}
	

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@SuppressWarnings("unchecked")
	public List<Version> getList(){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Version";
		return session.createQuery(hql)
				.setCacheable(true)//使用查询缓存
				.list();
	}


	public Version findByVersion(String ver) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Version v WHERE v.ver=:ver";
		return (Version) session.createQuery(hql)//
				.setCacheable(true)//
				.setParameter("ver", ver)//
				.uniqueResult();
	}
}
