package test;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import mapper.Demo9_UserMapper;
import mybatis.po.UserCustom;
import mybatis.po.UserQueryVo;

public class Demo9_UserMapperTest {

	private static SqlSessionFactory sqlSessionFactory;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
	}

	@Test
	public void testFindUserList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Demo9_UserMapper userMapper = sqlSession.getMapper(Demo9_UserMapper.class);
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.getIdList().add(1);
		userQueryVo.getIdList().add(10);
		userQueryVo.getIdList().add(24);
		List<UserCustom> userCustomList = userMapper.findUserList(userQueryVo);
		for (UserCustom userCustom : userCustomList) {
			System.out.println(userCustom);
		}
		sqlSession.close();
		
	}

}
