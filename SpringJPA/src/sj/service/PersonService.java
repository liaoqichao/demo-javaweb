package sj.service;

import java.util.List;

import sj.domain.Person;

public interface PersonService {

	void save(Person person);

	void update(Person person);

	void delete(Integer id);

	Person getPerson(Integer id);

	List<Person> getPersonList();

}