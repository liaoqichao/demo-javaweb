package sj.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sj.domain.Person;
import sj.service.PersonService;

@Transactional
public class PersonServiceBean implements PersonService {

	@PersistenceContext	//这个注解用于注入EntityManager对象，从EntityMangerFactory中创建EntityManager对象
	EntityManager em;
	
	@Override
	public void save(Person person){
		em.persist(person);
	}
	
	@Override
	public void update(Person person){
		em.merge(person);
	}
	
	@Override
	public void delete(Integer id){
		/*
		 * em.getReference相当于session.load()都是懒加载。
		 */
		em.remove(em.getReference(Person.class, id));
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)//查询不用事务
	@Override
	public Person getPerson(Integer id){
		return em.getReference(Person.class, id);
	}
	
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)//查询不用事务
	@Override
	@SuppressWarnings("unchecked")
	public List<Person> getPersonList(){
		/*
		 * 查询语句是JBQL语句，关键字不区分大小写，和HQL，SQL一样
		 * 这里的o是别名
		 * Person是类
		 */
		return em.createQuery("SELECT o FROM Person o").getResultList();
	}
}
