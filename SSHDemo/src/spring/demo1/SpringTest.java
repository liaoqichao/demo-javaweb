package spring.demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ����spring�����Ƿ��ɹ�
 */
public class SpringTest {

	/**
	 * û����˵��������ɹ�
	 */
	@Test
	public void instanceSpring(){
//		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	}
}
