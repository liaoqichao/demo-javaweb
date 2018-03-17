package demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

/**
 * 动态代理之Waiter案例
 */
public class Demo2 {

	/**
	 * 给ManWaiter#service增强功能,在service输出的“服务中”前加“你好”，后加“再见”
	 */
	@Test
	public void fun1(){
		Waiter manWaiter = new ManWaiter();	//目标对象
		ClassLoader loader = this.getClass().getClassLoader();
		@SuppressWarnings("rawtypes")
		Class[] interfaces = {Waiter.class};
		InvocationHandler h = new WaiterInvocationHandler(manWaiter);
		Waiter waiterProxy = (Waiter)Proxy.newProxyInstance(loader, interfaces, h);//代理对象
		waiterProxy.service(); 
	}
}

class WaiterInvocationHandler implements InvocationHandler{

	private Waiter waiter;//目标对象
	public WaiterInvocationHandler(Waiter waiter){
		this.waiter = waiter;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//增强的内容
		System.out.println("你好");
		this.waiter.service();//调用目标对象的目标方法
		System.out.println("再见");
		return null;
	}
	
}
