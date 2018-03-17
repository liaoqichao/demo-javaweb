package demo10.client;

public class App {
	public static void main(String[] args) {
		SayHiService service = new SayHiService();
		SayHi sayHi = service.getSayHiPort();
		String str = sayHi.sayHi("уехЩ");
		System.out.println(str);
	}
}
