package demo8.client;
/**
 * ͨ��wsdl2java���ɿͻ��˴������WebService����
 * ��Ҫ�Դ�������޸ģ�import�������
 * @author Liaoqc
 *
 */
public class Client {
	public static void main(String[] args) {
		/*
		 * Service -> port -> operation
		 */
		HelloServiceService hss = new HelloServiceService();
		HelloService hs = hss.getHelloServicePort();
		String str = hs.sayHello("���˸���");
		System.out.println(str);
	}
	
}
