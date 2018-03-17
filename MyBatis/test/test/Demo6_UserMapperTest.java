package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import mapper.Demo6_UserMapper;
import mybatis.po.UserCustom;
import mybatis.po.UserQueryVo;

public class Demo6_UserMapperTest {

	private static SqlSessionFactory sqlSessionFactory;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = new SqlSessionFactoryBuilder().//
				build(Resources.getResourceAsStream("SqlMapConfig.xml"));
	}

	@Test
	public void testFindUserCount() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		Demo6_UserMapper userMapper = sqlSession.getMapper(Demo6_UserMapper.class);
		UserQueryVo userQueryVo = new UserQueryVo(); // 包装类型
		UserCustom userCustom = new UserCustom(); // 查询条件
		userCustom.setSex("1");
		userCustom.setUsername("三");
		userQueryVo.setUserCustom(userCustom); // 包装查询条件
		int count = userMapper.findUserCount(userQueryVo);
		System.out.println(count);
	}

}
