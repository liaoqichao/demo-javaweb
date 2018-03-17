package spring.demo10;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lqcUtils.proxy.BeforeAdvice;
import lqcUtils.proxy.ProxyFactory;
import spring.demo10.service.PersonService;
import spring.demo10.service.impl.PersonServiceBean;

public class TestDemo10 {

	/**
	 * 使用JDK的动态代理。要求:被代理的独享需要实现接口
	 * 把beanFactory中invocationHandler的invoke的method.invoke()方法前面加一句判断，判断
	 * String user是否空就可以。这里已经封装成工具类就不实现了。。
	 */
//	@Test
	public void test1(){
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		PersonService ps = (PersonService) ctx.getBean("demo10_personService");
		ProxyFactory pf = new ProxyFactory();
		
		pf.setTargetObject(ps);
		pf.setBeforeAdvice(new MyBeforeAdvice(""));
		PersonService psProxy = (PersonService)pf.createProxy();
		psProxy.save("123");
		psProxy.getPersonName(123);
		System.out.println(psProxy);
		ctx.close();
	}
	/**
	 * 使用cglib创建代理对象，被代理的对象不需要实现接口。
	 */
//	@Test
	public void test2(){
//		PersonServiceBean ps = new PersonServiceBean();
		PersonServiceBean ps = new PersonServiceBean("123");
		CGlibProxyFactory cglibFactory = new CGlibProxyFactory();
		//不能用接口来引用代理对象
		PersonServiceBean psProxy = (PersonServiceBean) cglibFactory.createProxy(ps);
		psProxy.save("123");
	}
	
} 

class MyBeforeAdvice implements BeforeAdvice{

	private String user ;
	
	public MyBeforeAdvice(){
		
	}
	public MyBeforeAdvice(String user){
		this.user = user;
	}
	@Override
	public void before() {
		if(user==null || user.trim().isEmpty()){
			System.out.println("我拦截你");
		}
	}
	
}