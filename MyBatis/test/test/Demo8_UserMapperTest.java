package test;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import mapper.Demo8_UserMapper;
import mybatis.po.UserCustom;
import mybatis.po.UserQueryVo;

public class Demo8_UserMapperTest {

	private static SqlSessionFactory sqlSessionFactory;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
	}

	@Test
	public void testFindUserList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Demo8_UserMapper userMapper = sqlSession.getMapper(Demo8_UserMapper.class);
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		// 因为使用了动态sql，如果不设置整个值，条件不会拼接到sql中，就算userQueryVo不传也不会报错
		userCustom.setSex("1");
//		userCustom.setUsername("三");
		userQueryVo.setUserCustom(userCustom);
		List<UserCustom> userCustomList = userMapper.findUserList(userQueryVo);
		for (UserCustom userCustom2 : userCustomList) {
			System.err.println(userCustom2);
		}
	}

	@Test
	public void testFindUserCount() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Demo8_UserMapper userMapper = sqlSession.getMapper(Demo8_UserMapper.class);
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		userCustom.setSex("1");
//		userCustom.setUsername("三");
		userQueryVo.setUserCustom(userCustom);
		int count  = userMapper.findUserCount(userQueryVo); // 7
		System.err.println(count);
	}

}
