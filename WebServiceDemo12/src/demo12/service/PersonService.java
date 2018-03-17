package demo12.service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import demo12.domain.Person;

@WebService
@BindingType(value=SOAPBinding.SOAP12HTTP_BINDING)
public interface PersonService {

	Person findPersonById(int id);
}
