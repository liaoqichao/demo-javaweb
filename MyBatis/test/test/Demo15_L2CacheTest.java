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
		 * 1. 注释掉mapper.xml中的<cache/>后，下面会执行2条sql语句
		 * mapper.xml中开启二级缓存后，下面只有1条sql语句
		 * 2. 
		 * 	2.1	开启二级缓存后第一次执行sql语句，缓存中没有。日志写着
		 * 		Cache Hit Ratio [mapper.Demo4_UserMapper]: 0.0
		 * 		缓存命中率为0，因为只在缓存找了一次，而且没找到。
		 * 	 2.2 执行第二次sql语句，日志写着Cache Hit Ratio [mapper.Demo4_UserMapper]: 0.5
		 * 		缓存命中率为0.5，因为一共找了2次，第一次缓存中没找到，第二次缓存中找到hashMap的key。
		 * 3. 在statement里面配置useCache=false，禁用二级缓存，每次查询都向数据库中查询。
		 */
		SqlSession sqlSession1 = this.sqlSessionFactory.openSession();
		SqlSession sqlSession2 = this.sqlSessionFactory.openSession();
		Demo4_UserMapper mapper1 = sqlSession1.getMapper(Demo4_UserMapper.class);
		// 第一次发起sql
		User user1 = mapper1.findUserById(1);
		System.out.println(user1);
		// 这里释放资源，将sqlSession中的数据写到二级缓存区域。
		sqlSession1.close();
		
		Demo4_UserMapper mapper2 = sqlSession2.getMapper(Demo4_UserMapper.class);
		User user2 = mapper2.findUserById(1); 
		System.out.println(user2);
		sqlSession2.close();
		
	}
//	@Test
	public void testL2Cache2() throws Exception{
		/*
		 * 第一次查询(sqlSession1)，缓存中没有查询到，向数据库中查询，查询完后写入二级缓存中。缓存命中率为0
		 * 第二次查询(sqlSession3)，缓存中查询到数据，缓存命中率为0.5
		 * sqlSession3执行commit后，清空缓存的数据。
		 * 第三次查询(sqlSession2),缓存中没有查询到，向数据库中查询，查询完后写入二级缓存中。命中率为0.33
		 */
		SqlSession sqlSession1 = this.sqlSessionFactory.openSession();
		SqlSession sqlSession2 = this.sqlSessionFactory.openSession();
		SqlSession sqlSession3 = this.sqlSessionFactory.openSession();
		Demo4_UserMapper mapper1 = sqlSession1.getMapper(Demo4_UserMapper.class);
		// 第一次发起sql
		User user1 = mapper1.findUserById(1);
		System.out.println(user1);
		// 这里释放资源，将sqlSession中的数据写到二级缓存区域。
		sqlSession1.close();
		
		// 执行提交事务，对应mapper的二级缓存区域清空
		Demo4_UserMapper mapper3 = sqlSession3.getMapper(Demo4_UserMapper.class);
		User user3 = mapper3.findUserById(1);
		user3.setUsername("王五五");
		mapper3.updateUser(user3);
		sqlSession3.commit(); // 提交事务，清空Demo4_UserMapper下的二级缓存
		sqlSession3.close();
		
		Demo4_UserMapper mapper2 = sqlSession2.getMapper(Demo4_UserMapper.class);
		User user2 = mapper2.findUserById(1); 
		System.out.println(user2);
		sqlSession2.close();
		
	}
}
