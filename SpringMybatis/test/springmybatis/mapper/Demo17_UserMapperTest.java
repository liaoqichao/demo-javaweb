package springmybatis.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springmybatis.po.User;

/**
 * mapper代理：一个一个mapper交给spring托管和批量扫描包下的mapper给spring托管。
 * 2种方法只是在beans.xml中配置不同.
 * 自动扫描getBean时候，bean的id为mapper接口的简单名称(首字母小写)
 * @author Liaoqichao
 * @date 2016年3月25日
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
		user.setAddress("mybatis和Spring整合，使用mapper代理");
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
		List<User> userList = userMapper.findUsersByName("三");
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
