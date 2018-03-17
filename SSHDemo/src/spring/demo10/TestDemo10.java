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
	 * ʹ��JDK�Ķ�̬����Ҫ��:������Ķ�����Ҫʵ�ֽӿ�
	 * ��beanFactory��invocationHandler��invoke��method.invoke()����ǰ���һ���жϣ��ж�
	 * String user�Ƿ�վͿ��ԡ������Ѿ���װ�ɹ�����Ͳ�ʵ���ˡ���
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
	 * ʹ��cglib����������󣬱�����Ķ�����Ҫʵ�ֽӿڡ�
	 */
//	@Test
	public void test2(){
//		PersonServiceBean ps = new PersonServiceBean();
		PersonServiceBean ps = new PersonServiceBean("123");
		CGlibProxyFactory cglibFactory = new CGlibProxyFactory();
		//�����ýӿ������ô������
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
			System.out.println("��������");
		}
	}
	
}