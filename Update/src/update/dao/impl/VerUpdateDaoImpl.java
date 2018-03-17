package update.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import update.dao.ClientBeanDao;
import update.dao.VerUpdateDao;
import update.domain.VerUpdate;

@Transactional
public class VerUpdateDaoImpl implements VerUpdateDao {

	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private ClientBeanDao clientBeanDao;
	
	public void save(VerUpdate verUpdate){
		Session session = sessionFactory.getCurrentSession();
		session.save(verUpdate);
	}
	
	public void delete(Integer id){
		Session session = sessionFactory.getCurrentSession();
		VerUpdate verUpdate = session.get(VerUpdate.class, id);
//		//先解除和ClientBean的关联关系再删除
//		Set<ClientBean> cbs = verUpdate.getClientBeanSet();
//		for (ClientBean clientBean : cbs) {
//			clientBean.setVersion(null);
//			clientBeanDao.update(clientBean);
//		}
		session.delete(verUpdate);
	}
	

	public void update(VerUpdate verUpdate){
		Session session = sessionFactory.getCurrentSession();
		session.update(verUpdate);
	}
	
	public VerUpdate get(Integer id){
		Session session = sessionFactory.getCurrentSession();
		return session.get(VerUpdate.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<VerUpdate> getList(){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM VerUpdate";
		return session.createQuery(hql)
				.setCacheable(true)//使用查询缓存
				.list();
	}
	
	public VerUpdate findByVersion(String version){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM VerUpdate v WHERE v._version.ver= :version ";
		return (VerUpdate) session.createQuery(hql)//
				.setParameter("version", version)//
				.uniqueResult();
	}
}
