package demo10.client;

public class App {
	public static void main(String[] args) {
		SayHiService service = new SayHiService();
		SayHi sayHi = service.getSayHiPort();
		String str = sayHi.sayHi("����");
		System.out.println(str);
	}
}
