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
 * ע�⣺�������ļ�Ҫ����ӳ���ļ�
 * @author Liaoqichao
 * @date 2016��3��11��
 */
public class Demo4_UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;
	@Before
	public void setUp() throws Exception {
		// ����SqlSessionFactory
		this.sqlSessionFactory = new SqlSessionFactoryBuilder().//
				build(Resources.getResourceAsStream("SqlMapConfig.xml"));
	}

//	@Test
	public void testFindUserById() throws Exception {
		/*
		 * 1.�����Ự
		 * 2.ͨ���Ự����mapper�ӿڵĴ������
		 * 3.���ýӿڵķ�����ɶ����ݿ�Ĳ���
		 * 4.�ͷ���Դ
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
		 * 1.�����Ự
		 * 2.ͨ���Ự�õ�mapper�ӿڵĴ������
		 * 3.����mapper�ӿڵķ�����ɶ����ݿ�Ĳ���
		 * 4.�ύ����
		 * 5.�ͷ���Դ
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
		sqlSession.commit(); // �����Ҫ��?��Ҫ��
		// 5.
		sqlSession.close();
	}

//	@Test
	public void testDeleteUserById() throws Exception {
		/*
		 * 1. �õ��Ự
		 * 2. ͨ���Ự�õ�mapper�ӿ�
		 * 3. ����mapper�ӿڷ����������ݿ�
		 * 4. �ύ����
		 * 5. �ͷ���Դ
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
		 * 1. �õ��Ự
		 * 2. ͨ���Ự�õ�mapper�ӿ�
		 * 3. ����mapper�ӿڵķ����������ݿ�
		 * 4. �ͷ���Դ
		 */
		
		// 1. 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 2. 
		Demo4_UserMapper userMapper = sqlSession.getMapper(Demo4_UserMapper.class);
		
		// 3.
		// ���ﲻ��Ҫ%��%����Ϊӳ���ļ�ʹ�õ���%${value}%���ַ���ƴ��
		List<User> userList = userMapper.findUsersByName("��"); 
		for (User user : userList) {
			System.err.println(user);
		}
		
		// 4.
		sqlSession.close();
	}

//	@Test
	public void testUpdateUser() throws Exception {
		/*
		 * 1. �õ��Ự
		 * 2. ͨ���Ự�õ�mapper
		 * 3. ����mapper�����������ݿ�
		 * 4. �ύ����
		 * 5. �ͷ���Դ
		 */
		// 1.
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 2.
		Demo4_UserMapper userMapper = sqlSession.getMapper(Demo4_UserMapper.class);
		// 3.
		User user = userMapper.findUserById(25);
		System.err.println(user);
		user.setUsername("����ǰ�ҽг�С��");
		user.setBirthday(new Date());
		userMapper.updateUser(user);
		// 4.
		sqlSession.commit();
		// 5.
		sqlSession.close();
	}

}
