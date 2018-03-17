package demo2.client;

public class Client {
	public static void main(String[] args) {
		/**
		 * 看http://127.0.0.1:6789/hello?wsdl调用
		 * 1.<service name="HelloServiceService">
		 * 		HelloServiceService hss = new HelloServiceService();
		 * 2.第一步中元素的子元素<port name="HelloServicePort" binding="tns:HelloServicePortBinding">
		 * 		HelloService hs = hhs.getHelloServicePort();
		 * 	其中返回类型根据binding属性所引用的tns:HelloServicePortBinding来找。找标签名为binding其name属性的值
		 *  为binding所引用的值的标签，这个标签的type属性的值就是该返回类型。
		 *  <binding name="HelloServicePortBinding" type="tns:HelloService">
		 * 3.通过hs调用已发布的方法，对应WSDL的operation标签。hs是代理对象
		 * 		hs.sayHello("张三");
		 * 	<operation name="sayHello">
		 * 4.关闭服务再次运行Client，报错！
		 */
		// 1.
		HelloServiceService hhs = new HelloServiceService();
		
		// 2. 
		HelloService hs = hhs.getHelloServicePort();
		
		// 3. 
		String str = hs.sayHello("张三");
		System.out.println(str); // hello 张三!
	}
}
