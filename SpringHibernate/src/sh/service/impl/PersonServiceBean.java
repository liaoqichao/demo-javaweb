package sh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sh.dao.PersonDao;
import sh.domain.Person;
import sh.service.PersonService;

@Transactional
@Service("personService")
public class PersonServiceBean implements PersonService {

	@Resource
	private PersonDao personDao;
	
	@Override
	public void save(Person person) {
		personDao.save(person);
	}

	@Override
	public void update(Person person) {
		personDao.update(person);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)//一般这两个属性都会同时出现
	@Override
	public Person getPerson(Integer id) {
		return personDao.getPerson(id);
	}

	@Override
	public void delete(Integer id) {
		personDao.delete(id);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)//一般这两个属性都会同时出现
	@Override
	public List<Person> getPersonList() {
		return personDao.getPersonList();
	}
	

	
}
