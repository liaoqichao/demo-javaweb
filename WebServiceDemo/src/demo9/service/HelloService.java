package demo9.service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService
@BindingType(value=SOAPBinding.SOAP12HTTP_BINDING) //使用SOAP1.2协议
public interface HelloService {

	String sayHello(String name);
}
