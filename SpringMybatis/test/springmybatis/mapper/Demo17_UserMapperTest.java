package springmybatis.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springmybatis.po.User;

/**
 * mapper����һ��һ��mapper����spring�йܺ�����ɨ����µ�mapper��spring�йܡ�
 * 2�ַ���ֻ����beans.xml�����ò�ͬ.
 * �Զ�ɨ��getBeanʱ��bean��idΪmapper�ӿڵļ�����(����ĸСд)
 * @author Liaoqichao
 * @date 2016��3��25��
 */
public class Demo17_UserMapperTest {

	private ApplicationContext ctx;
	@Before
	public void setUp() throws Exception {
		this.ctx = new ClassPathXmlApplicationContext("spring/beans.xml");
	
	}

	@Test
	public void testFindUserById() throws Exception {
		Demo17_UserMapper userMapper = (Demo17_UserMapper) ctx.getBean("demo17_UserMapper");
		User user = userMapper.findUserById(1);
		System.err.println(user);
	}

//	@Test
	public void testInsertUser() throws Exception {
		Demo17_UserMapper userMapper = (Demo17_UserMapper) ctx.getBean("demo17_UserMapper");
		User user = new User();
		user.setUsername("demo17");
		user.setAddress("mybatis��Spring���ϣ�ʹ��mapper����");
		userMapper.insertUser(user);
		
	}

//	@Test
	public void testDeleteUserById() throws Exception {
		Demo17_UserMapper userMapper = (Demo17_UserMapper) ctx.getBean("demo17_UserMapper");
		userMapper.deleteUserById(28);
	}

	@Test
	public void testFindUsersByName() throws Exception {
		Demo17_UserMapper userMapper = (Demo17_UserMapper) ctx.getBean("demo17_UserMapper");
		List<User> userList = userMapper.findUsersByName("��");
		for (User user : userList) {
			System.err.println(user);
		}
	}

//	@Test
	public void testUpdateUser() throws Exception {
		Demo17_UserMapper userMapper = (Demo17_UserMapper) ctx.getBean("demo17_UserMapper");
		User user = userMapper.findUserById(1);
		user.setBirthday(new Date());
		userMapper.updateUser(user);
	}

}
