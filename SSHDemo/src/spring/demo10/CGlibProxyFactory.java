package spring.demo10;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import spring.demo10.service.impl.PersonServiceBean;

/**
 * cglib��������Ҫʵ��MethodInterceptor�ӿڣ�ʵ��intercept������
 * callback�ص��ľ�����������������൱��invocationHandler��invoke
 */
public class CGlibProxyFactory implements MethodInterceptor{

	private Object targetObject;
	
	public Object createProxy(Object targetObject){
		this.targetObject = targetObject;
		//cglib����
		Enhancer enhancer = new Enhancer();
		//���ø��࣬����ΪĿ���ࡣ�����Ĵ������̳�Ŀ���࣬����Ŀ������final�Ĳ��ܱ��޸ġ������������ᱻoverride
		enhancer.setSuperclass(this.targetObject.getClass());
		//���ûص����������������ִ��ҵ�񷽷��������ǵķ�����ʱ����ִ������ص�������
		enhancer.setCallback(this);
		return enhancer.create();
	}

	/**
	 * �൱��invocationHandler��invoke
	 */
	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

		/*
		 * ������
		 * Object arg0�����������
		 * Method arg1�������صķ���
		 * Object[] arg2�� �����صķ����Ĳ���
		 * MethodProxy arg3�������Ĵ������
		 */
		
		//----����֪ͨ
		//����ת��
		PersonServiceBean bean = (PersonServiceBean)this.targetObject;
		Object result = null;
		if(bean.getUser() != null){
			//---ǰ��֪ͨ
			result = methodProxy.invoke(targetObject, args);
			//---����֪ͨ
		} else{
			System.out.println("userΪ�գ�����Ȩ����");
		}
		//---����֪ͨ����try-catch-finally�е�catch����
		//---����֪ͨ����try-catch-finally�е�finally����
		return result;
	}

}
