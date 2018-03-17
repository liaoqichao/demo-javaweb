
package demo10.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the demo10.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SayHiResponse_QNAME = new QName("http://impl.service.demo10/", "sayHiResponse");
    private final static QName _SayHi_QNAME = new QName("http://impl.service.demo10/", "sayHi");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: demo10.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayHiResponse }
     * 
     */
    public SayHiResponse createSayHiResponse() {
        return new SayHiResponse();
    }

    /**
     * Create an instance of {@link SayHi_Type }
     * 
     */
    public SayHi_Type createSayHi_Type() {
        return new SayHi_Type();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.demo10/", name = "sayHiResponse")
    public JAXBElement<SayHiResponse> createSayHiResponse(SayHiResponse value) {
        return new JAXBElement<SayHiResponse>(_SayHiResponse_QNAME, SayHiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHi_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.demo10/", name = "sayHi")
    public JAXBElement<SayHi_Type> createSayHi(SayHi_Type value) {
        return new JAXBElement<SayHi_Type>(_SayHi_QNAME, SayHi_Type.class, null, value);
    }

}
