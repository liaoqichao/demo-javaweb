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
		// ��Ϊʹ���˶�̬sql���������������ֵ����������ƴ�ӵ�sql�У�����userQueryVo����Ҳ���ᱨ��
		userCustom.setSex("1");
//		userCustom.setUsername("��");
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
//		userCustom.setUsername("��");
		userQueryVo.setUserCustom(userCustom);
		int count  = userMapper.findUserCount(userQueryVo); // 7
		System.err.println(count);
	}

}
