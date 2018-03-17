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

	@PersistenceContext	//���ע������ע��EntityManager���󣬴�EntityMangerFactory�д���EntityManager����
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
		 * em.getReference�൱��session.load()���������ء�
		 */
		em.remove(em.getReference(Person.class, id));
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)//��ѯ��������
	@Override
	public Person getPerson(Integer id){
		return em.getReference(Person.class, id);
	}
	
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)//��ѯ��������
	@Override
	@SuppressWarnings("unchecked")
	public List<Person> getPersonList(){
		/*
		 * ��ѯ�����JBQL��䣬�ؼ��ֲ����ִ�Сд����HQL��SQLһ��
		 * �����o�Ǳ���
		 * Person����
		 */
		return em.createQuery("SELECT o FROM Person o").getResultList();
	}
}
