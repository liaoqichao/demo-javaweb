package demo9.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import demo9.service.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		System.out.println("Hello "+name);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm;ss");
		return formatter.format(new Date())+" hello "+name;
	}

}
