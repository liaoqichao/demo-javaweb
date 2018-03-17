package demo9.service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService
@BindingType(value=SOAPBinding.SOAP12HTTP_BINDING) //ʹ��SOAP1.2Э��
public interface HelloService {

	String sayHello(String name);
}
