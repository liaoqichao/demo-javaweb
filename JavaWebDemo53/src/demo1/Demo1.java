package demo1;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

/**
 * 动态代理：
 * 1.创建代理对象
 * 2.三个参数：ClassLoader classloader，Class[] interfaces，InvocationHandler h
 * 3.InvocationHandler#invoke方法的3个参数和返回值对应代理对象：
 * 		接口的方法（代理对象调用的方法，也叫目标方法），目标方法的参数，目标方法的返回值
 * @author Administrator
 *
 */
public class Demo1 {

	@Test
	public void func1(){
		/*
		 * 三大参数
		 * 1.ClassLoader
		 * 方法需要动态生成一个类，这个类实现了A.B接口，然后创建这个类的对象
		 * 需要生成一个类，这个类也需要加载到内存的方法区中（谁来加载？classloader）
		 * 2.Class[] interfaces
		 * 要实现的接口
		 * 3.invocationHandler
		 * 它是调用处理器。只有一个invoke方法.
		 * 代理对象实现所有接口中的方法都是调用InvocationHandler#invoke
		 * 代理对象的所有非native方法都睡调用InvocationHandler#invoke
		 */
		ClassLoader loader = this.getClass().getClassLoader();//借用Demo1的类加载器来完成一个实现了A,B接口的新类的加载器
		
		InvocationHandler h = new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				/*
				 * 参数：
				 * proxy就是动态代理创建的对象
				 * method就是接口的方法（动态代理创建的对象调用的方法），method也叫目标方法
				 * args就是method方法所需要的参数，args是目标方法所需要的实参
				 * 返回值：
				 * 返回值就是接口方法（动态代理创建的对象调用的方法）的返回值
				 */
				System.out.println("你好，动态代理");
				return "xxx";//接口方法返回的对象
			}
			
		};
		/*
		 * 动态代理创建的对象，在硬盘上没有对应的.class文件，但是在方法区生成了Class对象
		 * 使用三大参数完成创建对象
		 */
		Object o = Proxy.newProxyInstance(loader, new Class[]{A.class,B.class}, h);
		//强转到接口类型就能使用接口的方法。
		A a = (A) o;
		B b = (B) o;
		
		a.a();//你好，动态代理
		a.aa();//你好，动态代理
		b.b();//你好，动态代理
		b.bb();//你好，动态代理
		o.toString();//这个输出也是调用了InvocationHandler#invoke方法。
		//o.equal(null)也执行这个方法。但是o.getClass不执行。native方法都不会执行invoke，其他都会。
//		但是hashCode也是native却执行了invoke
		System.out.println(o.getClass().getName());//$Proxy4 类名。我们往往只关心实现了接口了没有，而不关心它是什么类型
		
		Object obj = a.aaa("hello", 100);
		/*
		 * 这里的a对象对应invocationHandler#invoke的参数Object proxy
		 * 这里的aaa方法对应invocationHandler#invoke的参数的Method method
		 * 这里的"hello",100对应了invocationHandler#invoke的参数的Object[] args
		 * 这里的Object obj对应了invocationHandler#invoke的返回值
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