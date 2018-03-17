package demo2.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

//�޸�wsdl�ļ���Ӧ��service��ǩname���Ե�ֵ
@WebService(serviceName="HelloWS",targetNamespace="http://www.baidu.com",portName="HelloServiceSOAP")
public class HelloService {

	public static void main(String[] args) {
		/**
		 * ����1������ķ�����ַ�������˿ںź�����·��url��
		 * ����2�������ʵ����
		 */
		System.out.println("��ʼ����WebService...");
		// ����Ҫд�����ĵ�ַ��
		Endpoint.publish("http://127.0.0.1:6789/hello", new HelloService());
	}
	
	@WebMethod(operationName="sayHelloABC") // �޸�wsdl�ļ��ϵķ������֣����ɵ�.java�ļ�������Ҳ���
	@WebResult(name="myReturn") // �޸�wsdl�ļ��ķ���ֵ�����֡�Ĭ��Ϊreturn
	public String sayHello(@WebParam(name="name") String name ){ //�޸�wsdl�ļ��ϵĲ�������Ĭ��Ϊarg0,arg1,...
		System.out.println("sayHello������...");
		return "hello "+name+"!";
	}
	
	@WebMethod(exclude=true) // ������������⹫��
	public String sayHello2222(String name){
		return "hello2222 "+name+"!";
	}
}
