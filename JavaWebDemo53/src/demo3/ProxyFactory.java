package demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ���������������ɴ������
 * ����Ҫ���еĲ�����
 * 1.Ŀ�����
 * 2.��ǿ����Ϊǰ����ǿ�ͺ�����ǿ
 * 
 * ʹ�ã�
 * 1.����������
 * 2.������������������
 * 	* Ŀ�����setTargetObject
 *  * ǰ����ǿ��setBeforeAdvice(BeforeAdvice�ӿڵ�ʵ��)
 *  * ������ǿ��setAfterAdvice(�ýӿڵ�ʵ��)
 * 3.����createProxy()�õ��������
 * 	* ִ�д�����󷽷�ʱ��
 * 		> ִ�е�BeforeAdvcie#before
 *  	> Ŀ������Ŀ�귽��
 *  	> ִ��AfterAdvice#after
 */
public class ProxyFactory {
	private Object targetObject;//Ŀ�����
	private BeforeAdvice beforeAdvice;//ǰ����ǿ��
	private AfterAdvice afterAdvice;//������ǿ
	
	/**
	 * �������ɴ������
	 * @return �������
	 */
	public Object createProxy(){
		/*
		 * 1.�����������
		 */
		ClassLoader loader = this.getClass().getClassLoader();
		@SuppressWarnings("rawtypes")
		Class[] interfaces = targetObject.getClass().getInterfaces();//��ȡĿ���������͵�����ʵ�ֽӿ�
		InvocationHandler h = new InvocationHandler(){

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				/*
				 * ���ô������ķ���ʱ��ִ����������ݡ�
				 */
				//ִ��ǰ����ǿ
				if(beforeAdvice != null){
					beforeAdvice.before();
				}
				Object obj = method.invoke(targetObject, args);//����Ŀ������Ŀ�귽��
				//ִ�к�����ǿ
				if(afterAdvice != null){
					afterAdvice.after();
				}
				//����Ŀ�����ķ���ֵ
				return obj;
			}
			
		};
		/*
		 * 2.�õ��������
		 */
		Object proxyObject = Proxy.newProxyInstance(loader, interfaces, h);
		/*
		 * ���ش������
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
