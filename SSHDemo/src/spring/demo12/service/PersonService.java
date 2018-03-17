package spring.demo12.service;

import java.util.List;

import spring.demo12.domain.Person;

public interface PersonService {

	public void save(Person person);
	
	public void update(Person person);
	
	public Person getPerson(Integer id);
	
	public List<Person> getPersonList();
	
	public void delete(Integer id);
}
