package demo10.client1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2016-03-06T14:23:07.002+08:00
 * Generated source version: 2.4.2
 * 
 */
@WebService(targetNamespace = "http://service.demo10/", name = "HelloService")
@XmlSeeAlso({})
public interface HelloService {

    @WebMethod
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://service.demo10/", className = "demo10.client1.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://service.demo10/", className = "demo10.client1.SayHelloResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String sayHello(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
