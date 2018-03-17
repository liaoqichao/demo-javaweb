package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import mapper.Demo4_UserMapper;
import mybatis.po.User;

public class Demo16_Ehcache {

	private SqlSessionFactory sqlSessionFactory;
	
	public Demo16_Ehcache() throws Exception{
		this.sqlSessionFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
		
	}
	
	@Test
	public void testEhCache1() throws Exception{
		/*
		 * diskStoreָ����E:\Eclipse\ehcache�ļ�����
		 * ������.ehcache-diskstore.lock�ļ���mapper%002e%0044emo4_%0055ser%004dapper.data�ļ�
		 * ��һ��������Ϊ0���ڶ���������Ϊ0.5
		 * ע��ǰ������˶������棬�ǵÿ�����
		 */
		SqlSession sqlSession1 = this.sqlSessionFactory.openSession();
		SqlSession sqlSession2 = this.sqlSessionFactory.openSession();
		Demo4_UserMapper mapper1 = sqlSession1.getMapper(Demo4_UserMapper.class);
		// ��һ�η���sql
		User user1 = mapper1.findUserById(1);
		System.out.println(user1);
		// �����ͷ���Դ����sqlSession�е�����д��������������
		sqlSession1.close();
		
		Demo4_UserMapper mapper2 = sqlSession2.getMapper(Demo4_UserMapper.class);
		User user2 = mapper2.findUserById(1); 
		System.out.println(user2);
		sqlSession2.close();
		
	}
}
