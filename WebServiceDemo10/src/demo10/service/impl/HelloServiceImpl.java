package demo10.service.impl;

import demo10.service.HelloService;

public class HelloServiceImpl implements HelloService{

	@Override
	public String sayHello(String name) {
		System.out.println("Hello "+name);
		return "Hello "+name;
	}

}
