package demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

/**
 * ��̬����֮Waiter����
 */
public class Demo2 {

	/**
	 * ��ManWaiter#service��ǿ����,��service����ġ������С�ǰ�ӡ���á�����ӡ��ټ���
	 */
	@Test
	public void fun1(){
		Waiter manWaiter = new ManWaiter();	//Ŀ�����
		ClassLoader loader = this.getClass().getClassLoader();
		@SuppressWarnings("rawtypes")
		Class[] interfaces = {Waiter.class};
		InvocationHandler h = new WaiterInvocationHandler(manWaiter);
		Waiter waiterProxy = (Waiter)Proxy.newProxyInstance(loader, interfaces, h);//�������
		waiterProxy.service(); 
	}
}

class WaiterInvocationHandler implements InvocationHandler{

	private Waiter waiter;//Ŀ�����
	public WaiterInvocationHandler(Waiter waiter){
		this.waiter = waiter;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//��ǿ������
		System.out.println("���");
		this.waiter.service();//����Ŀ������Ŀ�귽��
		System.out.println("�ټ�");
		return null;
	}
	
}
