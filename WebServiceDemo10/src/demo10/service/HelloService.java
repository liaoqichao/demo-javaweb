package demo10.service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService
@BindingType(value=SOAPBinding.SOAP12HTTP_BINDING)
public interface HelloService {

	String sayHello(String name);
}
