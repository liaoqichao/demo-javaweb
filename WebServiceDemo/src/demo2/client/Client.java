package demo2.client;

public class Client {
	public static void main(String[] args) {
		/**
		 * ��http://127.0.0.1:6789/hello?wsdl����
		 * 1.<service name="HelloServiceService">
		 * 		HelloServiceService hss = new HelloServiceService();
		 * 2.��һ����Ԫ�ص���Ԫ��<port name="HelloServicePort" binding="tns:HelloServicePortBinding">
		 * 		HelloService hs = hhs.getHelloServicePort();
		 * 	���з������͸���binding���������õ�tns:HelloServicePortBinding���ҡ��ұ�ǩ��Ϊbinding��name���Ե�ֵ
		 *  Ϊbinding�����õ�ֵ�ı�ǩ�������ǩ��type���Ե�ֵ���Ǹ÷������͡�
		 *  <binding name="HelloServicePortBinding" type="tns:HelloService">
		 * 3.ͨ��hs�����ѷ����ķ�������ӦWSDL��operation��ǩ��hs�Ǵ������
		 * 		hs.sayHello("����");
		 * 	<operation name="sayHello">
		 * 4.�رշ����ٴ�����Client������
		 */
		// 1.
		HelloServiceService hhs = new HelloServiceService();
		
		// 2. 
		HelloService hs = hhs.getHelloServicePort();
		
		// 3. 
		String str = hs.sayHello("����");
		System.out.println(str); // hello ����!
	}
}
