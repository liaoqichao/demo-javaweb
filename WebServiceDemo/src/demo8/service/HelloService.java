package demo8.service;

import javax.jws.WebService;

@WebService
public interface HelloService {

	public String sayHello(String name);
}
