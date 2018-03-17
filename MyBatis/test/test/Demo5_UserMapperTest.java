package test;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import mapper.Demo5_UserMapper;
import mybatis.po.UserCustom;
import mybatis.po.UserQueryVo;

public class Demo5_UserMapperTest {

	private SqlSessionFactory factory;
	
	@Before
	public void setUp() throws Exception {
		SqlSessionFactory factory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
		this.factory = factory;
	}

	@Test
	public void testFindUserList() throws Exception {
		SqlSession sqlSession = factory.openSession();
		Demo5_UserMapper userMapper = sqlSession.getMapper(Demo5_UserMapper.class);
		// 设置查询条件
		UserCustom userCustom = new UserCustom();
		userCustom.setSex("1");
		userCustom.setUsername("三");
		// 构建包装对象
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserCustom(userCustom);
		List<UserCustom> userCustomList = userMapper.findUserList(userQueryVo);
		for (UserCustom userCustom2 : userCustomList) {
			System.out.println(userCustom2);
		}
	}

}
