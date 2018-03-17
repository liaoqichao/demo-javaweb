package spring.demo12.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.demo12.domain.Person;
import spring.demo12.service.PersonService;

@Service("demo12_personService")
public class PersonServiceBean implements PersonService {

	@Override
	public void save(Person person){
		
	}

	@Override
	public void update(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person getPerson(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getPersonList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
