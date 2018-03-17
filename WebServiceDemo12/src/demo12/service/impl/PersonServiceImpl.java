package demo12.service.impl;

import demo12.domain.Person;
import demo12.service.PersonService;

public class PersonServiceImpl implements PersonService {

	@Override
	public Person findPersonById(int id) {
		Person p = new Person();
		p.setId(id);
		p.setAge(15);
		p.setGender("male");
		p.setName("ÍõÎå");
		return p;
	}

}
