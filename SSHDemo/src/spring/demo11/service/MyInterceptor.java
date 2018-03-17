package spring.demo11.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 使用注解指明这个类是切面。定义完切面后要交给spring管理，不然没有用。
 * 怎么交给spring管理呢? 切面也是类，就可以在配置文件中<bean>和使用注解@Component（spring自动扫描）
 */
@Aspect	//注解为切面，给对应的解析器解析。
@Component("demo11_myInterceptor")	//注解为组件，给对应的解析器解析，让spring自动扫描，管理bean。
public class MyInterceptor {

	/*
	 * 在切面里面要定义切入点
	 * 切入点：一组被增强的方法（连接点）。在spring中切入点只能是方法，但是AOP中切入点可以是属性，异常，构造器等
	 * 
	 * execution(* spring.demo11.service..*.*(..))
	 * execution：表示进行拦截。
	 * 第一个*：表示返回类型，*表示所有返回类型
	 * spring.demo11.service：包名
	 * ..：包括子包
	 * 第二个*：类，*表示所有类
	 * 第三个*：方法，*表示所有方法
	 * (..)：代表方法的参数为0~n个
	 * 
	 * "execution(* spring.demo11.service.impl.PersonServiceBean.*(..))"
	 * 
	 */
	@Pointcut("execution(!void spring.demo11.service.impl.PersonServiceBean.*(..))")
	private void anyMethod(){	
		//声明一个切入点
		System.out.println("声明一个切入点");
	}
	
	//org.aspectj.lang.annotation.Before这个包
	//前置通知，注解值为切入点名称，包括括号
	@Before(value = "anyMethod() && args(userName,id)")//有参数的情况
	public void doAccessCheck(String userName, Integer id){	
		System.out.println("我是带参数的前置增强。 "+userName + " : "+ id);
	}
	
	@Before(value = "anyMethod()")
	public void doAccessCheckNoArgs(){	//有参数会报错
		System.out.println("我是不带参数的前置增强");
	}
	
	@AfterReturning(pointcut="anyMethod()", returning="result")//returning获得目标发放的返回结果
	public void doAfterReturning(String result){
		System.out.println("我是后置增强，目标方法的返回结果是： "+result);
	}
	
	@After(value = "anyMethod()")	//注意导包，是aspectj下的包
	public void doAfter(){
		System.out.println("我是最终增强");
	}
	
	@AfterThrowing(pointcut="anyMethod()", throwing="e")//目标方法的异常对应异常增强的参数
	public void doAfterThrowing(Exception e){
		//输出顺序是前置增强->最终增强->异常增强
		System.out.println("我是处理异常增强，目标方法抛出异常后我才会执行 : "+e.getMessage());
	}
	
	/*
	 * 环绕增强，固定格式，就方法名和参数名可以变
	 * 环绕增强特别适合做权限控制。
	 */
	@Around("anyMethod()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
		/*
		 * 方法内部一定要执行pjp.proceed()方法
		 * 如果不执行这个方法，那么在这个切面后面的切面的业务bean方法就不会被执行
		 */
		Object result = null;
		if(true){	//判断用户是否有权限，有权限就调用这个方法，放行
			System.out.println("进入环绕增强");
			result = pjp.proceed();	
			System.out.println("退出环绕增强");
		} 
//		else{//没有权限就不调用这个方法，被拦截。
//		}
		return result;
	}
}
