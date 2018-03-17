package demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂：用来生成代理对象。
 * 它需要所有的参数：
 * 1.目标对象
 * 2.增强：分为前置增强和后置增强
 * 
 * 使用：
 * 1.创建代理工厂
 * 2.给工厂设置三个参数
 * 	* 目标对象：setTargetObject
 *  * 前置增强：setBeforeAdvice(BeforeAdvice接口的实现)
 *  * 后置增强：setAfterAdvice(该接口的实现)
 * 3.调用createProxy()得到代理对象
 * 	* 执行代理对象方法时：
 * 		> 执行的BeforeAdvcie#before
 *  	> 目标对象的目标方法
 *  	> 执行AfterAdvice#after
 */
public class ProxyFactory {
	private Object targetObject;//目标对象
	private BeforeAdvice beforeAdvice;//前置增强。
	private AfterAdvice afterAdvice;//后置增强
	
	/**
	 * 用来生成代理对象
	 * @return 代理对象
	 */
	public Object createProxy(){
		/*
		 * 1.给出三大参数
		 */
		ClassLoader loader = this.getClass().getClassLoader();
		@SuppressWarnings("rawtypes")
		Class[] interfaces = targetObject.getClass().getInterfaces();//获取目标对象的类型的所有实现接口
		InvocationHandler h = new InvocationHandler(){

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				/*
				 * 调用代理对象的方法时会执行这里的内容。
				 */
				//执行前置增强
				if(beforeAdvice != null){
					beforeAdvice.before();
				}
				Object obj = method.invoke(targetObject, args);//调用目标对象的目标方法
				//执行后置增强
				if(afterAdvice != null){
					afterAdvice.after();
				}
				//返回目标对象的返回值
				return obj;
			}
			
		};
		/*
		 * 2.得到代理对象
		 */
		Object proxyObject = Proxy.newProxyInstance(loader, interfaces, h);
		/*
		 * 返回代理对象
		 */
		return proxyObject;
	}
	public Object getTargetObject() {
		return targetObject;
	}
	public void setTargetObject(Object targetObject) {
		this.targetObject = targetObject;
	}
	public BeforeAdvice getBeforeAdvice() {
		return beforeAdvice;
	}
	public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
		this.beforeAdvice = beforeAdvice;
	}
	public AfterAdvice getAfterAdvice() {
		return afterAdvice;
	}
	public void setAfterAdvice(AfterAdvice afterAdvice) {
		this.afterAdvice = afterAdvice;
	}
	
	
}
