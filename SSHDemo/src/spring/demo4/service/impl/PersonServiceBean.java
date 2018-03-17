package spring.demo4.service.impl;

import spring.demo4.service.PersonService;

public class PersonServiceBean implements PersonService {

	/* (non-Javadoc)
	 * @see spring.demo4.service.impl.PersonService#save()
	 */
	@Override
	public void save(){
		System.out.println("person±£¥Ê¡À£°");
	}
}
