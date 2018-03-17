package demo10.service.impl;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService
@BindingType(value=SOAPBinding.SOAP12HTTP_BINDING) // �л���1.2Э��
public class SayHi {

	public String sayHi(String name){
		System.out.println("Hi "+name);
		return "Hi "+name;
	}
}
