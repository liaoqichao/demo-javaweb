package sh.service;

import java.util.List;

import sh.domain.Person;

public interface PersonService {

	void save(Person person);

	void update(Person person);

	Person getPerson(Integer id);

	void delete(Integer id);

	List<Person> getPersonList();

}