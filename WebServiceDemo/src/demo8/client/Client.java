package demo8.client;
/**
 * 通过wsdl2java生成客户端代码调用WebService服务
 * 需要对代码进行修改（import那里出错）
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
		String str = hs.sayHello("喵了个咪");
		System.out.println(str);
	}
	
}
