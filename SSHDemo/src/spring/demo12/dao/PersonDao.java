package spring.demo12.dao;

import java.util.List;

import spring.demo12.domain.Person;

public interface PersonDao {

	public void save(Person person);
	
	public void update(Person person);
	
	public Person getPerson(Integer id);
	
	public List<Person> getPersonList();
	
	public void delete(Integer id) throws Exception;
}
