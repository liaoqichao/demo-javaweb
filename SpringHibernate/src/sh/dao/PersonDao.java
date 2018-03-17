package sh.dao;

import java.util.List;

import sh.domain.Person;

public interface PersonDao {

	void save(Person person);

	void update(Person person);

	Person getPerson(Integer id);

	void delete(Integer id);

	List<Person> getPersonList();
}
