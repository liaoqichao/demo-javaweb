package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import mapper.Demo7_UserMapper;
import mybatis.po.User;

public class Demo7_UserMapperTest {

	private static SqlSessionFactory sqlSessionFactory;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
	}

	@Test
	public void testFindUserByIdResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Demo7_UserMapper userMapper = sqlSession.getMapper(Demo7_UserMapper.class);
		User user = userMapper.findUserByIdResultMap(235);
		System.err.println(user);
		//User [id=235, username=英雄何不随风起，扶摇直上九万里, birthday=null, sex=null, address=null]
	}

}
