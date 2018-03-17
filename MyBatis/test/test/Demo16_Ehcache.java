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
		 * diskStore指定的E:\Eclipse\ehcache文件夹下
		 * 生成了.ehcache-diskstore.lock文件和mapper%002e%0044emo4_%0055ser%004dapper.data文件
		 * 第一次命中率为0，第二次命中率为0.5
		 * 注意前面禁用了二级缓存，记得开启。
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
}
