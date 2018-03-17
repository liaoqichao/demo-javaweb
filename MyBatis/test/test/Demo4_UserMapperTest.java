package test;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import mapper.Demo4_UserMapper;
import mybatis.po.User;

/**
 * 注意：主配置文件要加载映射文件
 * @author Liaoqichao
 * @date 2016年3月11日
 */
public class Demo4_UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		// 创建SqlSessionFactory
		this.sqlSessionFactory = new SqlSessionFactoryBuilder().//
				build(Resources.getResourceAsStream("SqlMapConfig.xml"));
	}

//	@Test
	public void testFindUserById() throws Exception {
		/*
		 * 1.创建会话
		 * 2.通过会话创建mapper接口的代理对象
		 * 3.调用接口的方法完成对数据库的操作
		 * 4.释放资源
		 */
		// 1.
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 2.
		Demo4_UserMapper userMapper = sqlSession.getMapper(Demo4_UserMapper.class);
		// 3.
		User user = userMapper.findUserById(10);
		System.err.println(user);
		// 4.
		sqlSession.close();
	}

//	@Test
	public void testInsertUser() throws Exception {
		/*
		 * 1.创建会话
		 * 2.通过会话得到mapper接口的代理对象
		 * 3.调用mapper接口的方法完成对数据库的操作
		 * 4.提交事务
		 * 5.释放资源
		 */
		// 1. 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 2. 
		Demo4_UserMapper userMapper = sqlSession.getMapper(Demo4_UserMapper.class);
		// 3. 
		User user = new User();
		user.setUsername("demo4");
		user.setBirthday(new Date());
		user.setSex("2");
		user.setAddress("demo4_address");
		
		userMapper.insertUser(user);
		// 4.
		sqlSession.commit(); // 这句需要吗?需要！
		// 5.
		sqlSession.close();
	}

//	@Test
	public void testDeleteUserById() throws Exception {
		/*
		 * 1. 得到会话
		 * 2. 通过会话得到mapper接口
		 * 3. 调用mapper接口方法操作数据库
		 * 4. 提交事务
		 * 5. 释放资源
		 */
		// 1. 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 2. 
		Demo4_UserMapper userMapper = sqlSession.getMapper(Demo4_UserMapper.class);
		// 3. 
		userMapper.deleteUserById(239);
		// 4. 
		sqlSession.commit();
		// 5.
		sqlSession.close();
	}

	@Test
	public void testFindUsersByName() throws Exception {
		/*
		 * 1. 得到会话
		 * 2. 通过会话得到mapper接口
		 * 3. 调用mapper接口的方法操作数据库
		 * 4. 释放资源
		 */
		
		// 1. 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 2. 
		Demo4_UserMapper userMapper = sqlSession.getMapper(Demo4_UserMapper.class);
		
		// 3.
		// 这里不需要%三%，因为映射文件使用的是%${value}%，字符串拼接
		List<User> userList = userMapper.findUsersByName("三"); 
		for (User user : userList) {
			System.err.println(user);
		}
		
		// 4.
		sqlSession.close();
	}

//	@Test
	public void testUpdateUser() throws Exception {
		/*
		 * 1. 得到会话
		 * 2. 通过会话得到mapper
		 * 3. 调用mapper方法操作数据库
		 * 4. 提交事务
		 * 5. 释放资源
		 */
		// 1.
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 2.
		Demo4_UserMapper userMapper = sqlSession.getMapper(Demo4_UserMapper.class);
		// 3.
		User user = userMapper.findUserById(25);
		System.err.println(user);
		user.setUsername("更新前我叫陈小明");
		user.setBirthday(new Date());
		userMapper.updateUser(user);
		// 4.
		sqlSession.commit();
		// 5.
		sqlSession.close();
	}

}
