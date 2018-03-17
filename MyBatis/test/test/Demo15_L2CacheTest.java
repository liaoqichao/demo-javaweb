package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import mapper.Demo4_UserMapper;
import mybatis.po.User;

public class Demo15_L2CacheTest {

	private SqlSessionFactory sqlSessionFactory;
	
	public Demo15_L2CacheTest() throws Exception{
		this.sqlSessionFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
		
	}
	
	@Test
	public void testL2Cache1() throws Exception{
		/*
		 * 1. ע�͵�mapper.xml�е�<cache/>�������ִ��2��sql���
		 * mapper.xml�п����������������ֻ��1��sql���
		 * 2. 
		 * 	2.1	��������������һ��ִ��sql��䣬������û�С���־д��
		 * 		Cache Hit Ratio [mapper.Demo4_UserMapper]: 0.0
		 * 		����������Ϊ0����Ϊֻ�ڻ�������һ�Σ�����û�ҵ���
		 * 	 2.2 ִ�еڶ���sql��䣬��־д��Cache Hit Ratio [mapper.Demo4_UserMapper]: 0.5
		 * 		����������Ϊ0.5����Ϊһ������2�Σ���һ�λ�����û�ҵ����ڶ��λ������ҵ�hashMap��key��
		 * 3. ��statement��������useCache=false�����ö������棬ÿ�β�ѯ�������ݿ��в�ѯ��
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
//	@Test
	public void testL2Cache2() throws Exception{
		/*
		 * ��һ�β�ѯ(sqlSession1)��������û�в�ѯ���������ݿ��в�ѯ����ѯ���д����������С�����������Ϊ0
		 * �ڶ��β�ѯ(sqlSession3)�������в�ѯ�����ݣ�����������Ϊ0.5
		 * sqlSession3ִ��commit����ջ�������ݡ�
		 * �����β�ѯ(sqlSession2),������û�в�ѯ���������ݿ��в�ѯ����ѯ���д����������С�������Ϊ0.33
		 */
		SqlSession sqlSession1 = this.sqlSessionFactory.openSession();
		SqlSession sqlSession2 = this.sqlSessionFactory.openSession();
		SqlSession sqlSession3 = this.sqlSessionFactory.openSession();
		Demo4_UserMapper mapper1 = sqlSession1.getMapper(Demo4_UserMapper.class);
		// ��һ�η���sql
		User user1 = mapper1.findUserById(1);
		System.out.println(user1);
		// �����ͷ���Դ����sqlSession�е�����д��������������
		sqlSession1.close();
		
		// ִ���ύ���񣬶�Ӧmapper�Ķ��������������
		Demo4_UserMapper mapper3 = sqlSession3.getMapper(Demo4_UserMapper.class);
		User user3 = mapper3.findUserById(1);
		user3.setUsername("������");
		mapper3.updateUser(user3);
		sqlSession3.commit(); // �ύ�������Demo4_UserMapper�µĶ�������
		sqlSession3.close();
		
		Demo4_UserMapper mapper2 = sqlSession2.getMapper(Demo4_UserMapper.class);
		User user2 = mapper2.findUserById(1); 
		System.out.println(user2);
		sqlSession2.close();
		
	}
}
