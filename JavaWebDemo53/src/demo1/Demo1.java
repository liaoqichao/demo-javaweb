package demo1;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

/**
 * ��̬����
 * 1.�����������
 * 2.����������ClassLoader classloader��Class[] interfaces��InvocationHandler h
 * 3.InvocationHandler#invoke������3�������ͷ���ֵ��Ӧ�������
 * 		�ӿڵķ��������������õķ�����Ҳ��Ŀ�귽������Ŀ�귽���Ĳ�����Ŀ�귽���ķ���ֵ
 * @author Administrator
 *
 */
public class Demo1 {

	@Test
	public void func1(){
		/*
		 * �������
		 * 1.ClassLoader
		 * ������Ҫ��̬����һ���࣬�����ʵ����A.B�ӿڣ�Ȼ�󴴽������Ķ���
		 * ��Ҫ����һ���࣬�����Ҳ��Ҫ���ص��ڴ�ķ������У�˭�����أ�classloader��
		 * 2.Class[] interfaces
		 * Ҫʵ�ֵĽӿ�
		 * 3.invocationHandler
		 * ���ǵ��ô�������ֻ��һ��invoke����.
		 * �������ʵ�����нӿ��еķ������ǵ���InvocationHandler#invoke
		 * �����������з�native������˯����InvocationHandler#invoke
		 */
		ClassLoader loader = this.getClass().getClassLoader();//����Demo1��������������һ��ʵ����A,B�ӿڵ�����ļ�����
		
		InvocationHandler h = new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				/*
				 * ������
				 * proxy���Ƕ�̬�������Ķ���
				 * method���ǽӿڵķ�������̬�������Ķ�����õķ�������methodҲ��Ŀ�귽��
				 * args����method��������Ҫ�Ĳ�����args��Ŀ�귽������Ҫ��ʵ��
				 * ����ֵ��
				 * ����ֵ���ǽӿڷ�������̬�������Ķ�����õķ������ķ���ֵ
				 */
				System.out.println("��ã���̬����");
				return "xxx";//�ӿڷ������صĶ���
			}
			
		};
		/*
		 * ��̬�������Ķ�����Ӳ����û�ж�Ӧ��.class�ļ��������ڷ�����������Class����
		 * ʹ�����������ɴ�������
		 */
		Object o = Proxy.newProxyInstance(loader, new Class[]{A.class,B.class}, h);
		//ǿת���ӿ����;���ʹ�ýӿڵķ�����
		A a = (A) o;
		B b = (B) o;
		
		a.a();//��ã���̬����
		a.aa();//��ã���̬����
		b.b();//��ã���̬����
		b.bb();//��ã���̬����
		o.toString();//������Ҳ�ǵ�����InvocationHandler#invoke������
		//o.equal(null)Ҳִ���������������o.getClass��ִ�С�native����������ִ��invoke���������ᡣ
//		����hashCodeҲ��nativeȴִ����invoke
		System.out.println(o.getClass().getName());//$Proxy4 ��������������ֻ����ʵ���˽ӿ���û�У�������������ʲô����
		
		Object obj = a.aaa("hello", 100);
		/*
		 * �����a�����ӦinvocationHandler#invoke�Ĳ���Object proxy
		 * �����aaa������ӦinvocationHandler#invoke�Ĳ�����Method method
		 * �����"hello",100��Ӧ��invocationHandler#invoke�Ĳ�����Object[] args
		 * �����Object obj��Ӧ��invocationHandler#invoke�ķ���ֵ
		 */
		System.out.println(obj);
	}
}

interface A {
	
	public void a();
	public void aa();
	public Object aaa(String s, int i);
}
interface B {
	public void b();
	public void bb();
}