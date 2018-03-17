package demo2.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

//修改wsdl文件对应的service标签name属性的值
@WebService(serviceName="HelloWS",targetNamespace="http://www.baidu.com",portName="HelloServiceSOAP")
public class HelloService {

	public static void main(String[] args) {
		/**
		 * 参数1：服务的发布地址（包括端口号和请求路径url）
		 * 参数2：服务的实现者
		 */
		System.out.println("开始发布WebService...");
		// 这里要写本机的地址。
		Endpoint.publish("http://127.0.0.1:6789/hello", new HelloService());
	}
	
	@WebMethod(operationName="sayHelloABC") // 修改wsdl文件上的方法名字，生成的.java文件方法名也会变
	@WebResult(name="myReturn") // 修改wsdl文件的返回值的名字。默认为return
	public String sayHello(@WebParam(name="name") String name ){ //修改wsdl文件上的参数名，默认为arg0,arg1,...
		System.out.println("sayHello被调用...");
		return "hello "+name+"!";
	}
	
	@WebMethod(exclude=true) // 这个方法不对外公布
	public String sayHello2222(String name){
		return "hello2222 "+name+"!";
	}
}
