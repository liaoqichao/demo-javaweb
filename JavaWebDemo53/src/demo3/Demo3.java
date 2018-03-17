package demo3;

import org.junit.Test;

/**
 * Ŀ������Ŀ��������ǿ�������л���
 */
public class Demo3 {

	@Test
	public void fun1(){
		ProxyFactory pf = new ProxyFactory();//��������
		pf.setTargetObject(new ManWaiter());//����Ŀ�����
		pf.setBeforeAdvice(new BeforeAdvice(){//����ǰ����ǿ
			 
			@Override
			public void before() {
				System.out.println("��ã�");
			}
			
		});
		pf.setAfterAdvice(new AfterAdvice() { //���ú�����ǿ
			
			@Override
			public void after() {
				System.out.println("�ټ���");
			}
		});
		
		Waiter waiter = (Waiter)pf.createProxy();
		waiter.service();
		waiter.shouQian();
	}
}
