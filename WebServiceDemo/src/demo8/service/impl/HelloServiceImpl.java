package demo8.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import demo8.service.HelloService;
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Hello "+name);
		return formatter.format(new Date())+" : Hello "+name;
	}

}
