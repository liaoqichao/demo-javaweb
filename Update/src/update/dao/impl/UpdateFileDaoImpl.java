package update.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import update.dao.UpdateFileDao;
import update.domain.UpdateFile;

@Transactional
public class UpdateFileDaoImpl implements UpdateFileDao {

	@Resource
	private SessionFactory sessionFactory;
	
	public void save(UpdateFile updateFile){
		Session session = sessionFactory.getCurrentSession();
		session.save(updateFile);
	}

	public void delete(Integer id){
		Session session = sessionFactory.getCurrentSession();
		UpdateFile updateFile = session.get(UpdateFile.class, id);
		session.delete(updateFile);
	}
	
	public void update(UpdateFile updateFile){
		Session session = sessionFactory.getCurrentSession();
		session.update(updateFile);
	}

	public UpdateFile get(Integer id){
		Session session = sessionFactory.getCurrentSession();
		return session.get(UpdateFile.class, id);
	}


	@SuppressWarnings("unchecked")
	public List<UpdateFile> getList(){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UpdateFile";
		return session.createQuery(hql)
				.setCacheable(true)//使用查询缓存
				.list();
	}
}
