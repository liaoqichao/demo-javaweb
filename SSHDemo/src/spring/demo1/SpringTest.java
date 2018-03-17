package spring.demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试spring环境是否搭建成功
 */
public class SpringTest {

	/**
	 * 没报错说明环境搭建成功
	 */
	@Test
	public void instanceSpring(){
//		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	}
}
