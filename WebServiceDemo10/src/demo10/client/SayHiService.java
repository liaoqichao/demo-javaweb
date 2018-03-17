package demo10.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2016-03-06T13:25:19.684+08:00
 * Generated source version: 2.4.2
 * 
 */
@WebServiceClient(name = "SayHiService", 
                  wsdlLocation = "http://localhost:8080/WebServiceDemo10/cxf/sayHi?wsdl",
                  targetNamespace = "http://impl.service.demo10/") 
public class SayHiService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://impl.service.demo10/", "SayHiService");
    public final static QName SayHiPort = new QName("http://impl.service.demo10/", "SayHiPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/WebServiceDemo10/cxf/sayHi?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SayHiService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/WebServiceDemo10/cxf/sayHi?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SayHiService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SayHiService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SayHiService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SayHiService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SayHiService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SayHiService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns SayHi
     */
    @WebEndpoint(name = "SayHiPort")
    public SayHi getSayHiPort() {
        return super.getPort(SayHiPort, SayHi.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SayHi
     */
    @WebEndpoint(name = "SayHiPort")
    public SayHi getSayHiPort(WebServiceFeature... features) {
        return super.getPort(SayHiPort, SayHi.class, features);
    }

}
