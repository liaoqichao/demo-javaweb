package spring.demo10;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import spring.demo10.service.impl.PersonServiceBean;

/**
 * cglib代理工厂需要实现MethodInterceptor接口，实现intercept方法。
 * callback回调的就是这个方法。这里相当于invocationHandler的invoke
 */
public class CGlibProxyFactory implements MethodInterceptor{

	private Object targetObject;
	
	public Object createProxy(Object targetObject){
		this.targetObject = targetObject;
		//cglib的类
		Enhancer enhancer = new Enhancer();
		//设置父类，父类为目标类。创建的代理类会继承目标类，所以目标类有final的不能被修改。其他方法都会被override
		enhancer.setSuperclass(this.targetObject.getClass());
		//设置回调函数。当代理对象执行业务方法（被覆盖的方法）时，会执行这个回调函数。
		enhancer.setCallback(this);
		return enhancer.create();
	}

	/**
	 * 相当于invocationHandler的invoke
	 */
	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

		/*
		 * 参数：
		 * Object arg0：代理对象本身
		 * Method arg1：被拦截的方法
		 * Object[] arg2： 被拦截的方法的参数
		 * MethodProxy arg3：方法的代理对象
		 */
		
		//----环绕通知
		//类型转换
		PersonServiceBean bean = (PersonServiceBean)this.targetObject;
		Object result = null;
		if(bean.getUser() != null){
			//---前置通知
			result = methodProxy.invoke(targetObject, args);
			//---后置通知
		} else{
			System.out.println("user为空，你无权访问");
		}
		//---例外通知。在try-catch-finally中的catch里面
		//---最终通知。在try-catch-finally中的finally里面
		return result;
	}

}
