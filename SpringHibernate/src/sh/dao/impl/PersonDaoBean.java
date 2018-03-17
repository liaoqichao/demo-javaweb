package sh.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sh.dao.PersonDao;
import sh.domain.Person;

@Repository("personDao")
public class PersonDaoBean implements PersonDao{

	
	@Resource
	private SessionFactory sessionFactory;
	@Override
	public void save(Person person) {
		/*
		 * beans.xml中的<prop>不需要添加
		 * <prop key="hibernate.current_session_context_class">thread</prop>?
		 * 
		 * 这里不能使用sessionFactory.openSession(),这样打开的session不受spring管理，只能使用getCurrentSession
		 * 获取本线程的session。
		 * 
		 * session.save和session.persist的区别。
		 * save：无论在事务内还是事务外都会立刻执行SQL插入语句。(如果有事务，会在事务提交前执行SQL插入语句)
		 * persist:只有在事务内才会执行SQL的插入语句
		 */
		Session session = sessionFactory.getCurrentSession();
		session.persist(person);
	}

	@Override
	public void update(Person person) {
		/*
		 * update，merge: 游离状态 -> 持久化状态
		 * 如果session中有的对象相同的持久化标识(就是数据库中的主键)，执行update方法会抛出异常。
		 * 而是用merge，session中本身就有的对象还是持久化状态，merge()的参数(持久化标识相同的另一个对象)，还是游离态(状态没改变)。
		 */
		Session session = sessionFactory.getCurrentSession();
		session.merge(person);
	}

	@Override
	public Person getPerson(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Person.class, id);
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Person person = session.load(Person.class, id);//游离态->持久化状态
		session.delete(person);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getPersonList() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Person";
		List<Person> personList = session.createQuery(hql)
				.setCacheable(true)
				.list();
		return personList;
	}

}
