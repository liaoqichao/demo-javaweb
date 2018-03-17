package spring.demo3.service.impl;

import spring.demo3.service.PersonService;

public class PersonServiceBean implements PersonService {

	/* (non-Javadoc)
	 * @see spring.demo2.service.impl.PersonService#save()
	 */
	@Override
	public void save(){
		System.out.println("person±£¥Ê¡À£°");
	}
}
