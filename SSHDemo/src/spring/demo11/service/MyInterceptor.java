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
 * ʹ��ע��ָ������������档�����������Ҫ����spring������Ȼû���á�
 * ��ô����spring������? ����Ҳ���࣬�Ϳ����������ļ���<bean>��ʹ��ע��@Component��spring�Զ�ɨ�裩
 */
@Aspect	//ע��Ϊ���棬����Ӧ�Ľ�����������
@Component("demo11_myInterceptor")	//ע��Ϊ���������Ӧ�Ľ�������������spring�Զ�ɨ�裬����bean��
public class MyInterceptor {

	/*
	 * ����������Ҫ���������
	 * ����㣺һ�鱻��ǿ�ķ��������ӵ㣩����spring�������ֻ���Ƿ���������AOP���������������ԣ��쳣����������
	 * 
	 * execution(* spring.demo11.service..*.*(..))
	 * execution����ʾ�������ء�
	 * ��һ��*����ʾ�������ͣ�*��ʾ���з�������
	 * spring.demo11.service������
	 * ..�������Ӱ�
	 * �ڶ���*���࣬*��ʾ������
	 * ������*��������*��ʾ���з���
	 * (..)���������Ĳ���Ϊ0~n��
	 * 
	 * "execution(* spring.demo11.service.impl.PersonServiceBean.*(..))"
	 * 
	 */
	@Pointcut("execution(!void spring.demo11.service.impl.PersonServiceBean.*(..))")
	private void anyMethod(){	
		//����һ�������
		System.out.println("����һ�������");
	}
	
	//org.aspectj.lang.annotation.Before�����
	//ǰ��֪ͨ��ע��ֵΪ��������ƣ���������
	@Before(value = "anyMethod() && args(userName,id)")//�в��������
	public void doAccessCheck(String userName, Integer id){	
		System.out.println("���Ǵ�������ǰ����ǿ�� "+userName + " : "+ id);
	}
	
	@Before(value = "anyMethod()")
	public void doAccessCheckNoArgs(){	//�в����ᱨ��
		System.out.println("���ǲ���������ǰ����ǿ");
	}
	
	@AfterReturning(pointcut="anyMethod()", returning="result")//returning���Ŀ�귢�ŵķ��ؽ��
	public void doAfterReturning(String result){
		System.out.println("���Ǻ�����ǿ��Ŀ�귽���ķ��ؽ���ǣ� "+result);
	}
	
	@After(value = "anyMethod()")	//ע�⵼������aspectj�µİ�
	public void doAfter(){
		System.out.println("����������ǿ");
	}
	
	@AfterThrowing(pointcut="anyMethod()", throwing="e")//Ŀ�귽�����쳣��Ӧ�쳣��ǿ�Ĳ���
	public void doAfterThrowing(Exception e){
		//���˳����ǰ����ǿ->������ǿ->�쳣��ǿ
		System.out.println("���Ǵ����쳣��ǿ��Ŀ�귽���׳��쳣���ҲŻ�ִ�� : "+e.getMessage());
	}
	
	/*
	 * ������ǿ���̶���ʽ���ͷ������Ͳ��������Ա�
	 * ������ǿ�ر��ʺ���Ȩ�޿��ơ�
	 */
	@Around("anyMethod()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
		/*
		 * �����ڲ�һ��Ҫִ��pjp.proceed()����
		 * �����ִ�������������ô������������������ҵ��bean�����Ͳ��ᱻִ��
		 */
		Object result = null;
		if(true){	//�ж��û��Ƿ���Ȩ�ޣ���Ȩ�޾͵����������������
			System.out.println("���뻷����ǿ");
			result = pjp.proceed();	
			System.out.println("�˳�������ǿ");
		} 
//		else{//û��Ȩ�޾Ͳ�������������������ء�
//		}
		return result;
	}
}
