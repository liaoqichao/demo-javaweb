package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import mapper.Demo4_UserMapper;
import mybatis.po.User;

public class Demo14_L1CacheTest {

	private SqlSessionFactory sqlSessionFactory;
	
	public Demo14_L1CacheTest() throws Exception{
		this.sqlSessionFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
		
	}
	
//	@Test
	public void testL1Cache1() throws Exception{
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		Demo4_UserMapper mapper = sqlSession.getMapper(Demo4_UserMapper.class);
		// 第一次发起请求，查询id为1的用户
		User user1 = mapper.findUserById(1);
		System.out.println(user1);
		// 第二次发起请求，查询id为1的用户
		User user2 = mapper.findUserById(1);  // 没有发出sql语句
		System.out.println(user2);
		sqlSession.close();
	}
	@Test
	public void testL1Cache2() throws Exception{
		/*
		 * 如果两次查询之间有commit，sqlSession会清空hashMap中的对象 
		 * 1. 如果两次查询之间有commit，则hashMap的对象清空，有2条查询语句
		 * 2. 如果commit放在最后，则先执行update，再执行第二次查询，第二次查询结果为更新后的结果。
		 * 		有第二次查询语句是因为hashMap的存储的user1对象已经被清空
		 */
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		Demo4_UserMapper mapper = sqlSession.getMapper(Demo4_UserMapper.class);
		// 第一次发起请求，查询id为1的用户
		User user1 = mapper.findUserById(1);
		System.out.println(user1);
		user1.setAddress("not nullaaa!");
		mapper.updateUser(user1);
		// 第二次发起请求，查询id为1的用户
		User user2 = mapper.findUserById(1);  // 没有发出sql语句
		System.out.println(user2);
		sqlSession.commit();
		sqlSession.close();
	}
}
