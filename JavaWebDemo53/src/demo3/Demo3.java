package demo3;

import org.junit.Test;

/**
 * 目标是让目标对象和增强都可以切换。
 */
public class Demo3 {

	@Test
	public void fun1(){
		ProxyFactory pf = new ProxyFactory();//创建工厂
		pf.setTargetObject(new ManWaiter());//设置目标对象
		pf.setBeforeAdvice(new BeforeAdvice(){//设置前置增强
			 
			@Override
			public void before() {
				System.out.println("你好！");
			}
			
		});
		pf.setAfterAdvice(new AfterAdvice() { //设置后置增强
			
			@Override
			public void after() {
				System.out.println("再见！");
			}
		});
		
		Waiter waiter = (Waiter)pf.createProxy();
		waiter.service();
		waiter.shouQian();
	}
}
