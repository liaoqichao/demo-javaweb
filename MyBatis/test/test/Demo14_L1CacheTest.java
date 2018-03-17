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
		// ��һ�η������󣬲�ѯidΪ1���û�
		User user1 = mapper.findUserById(1);
		System.out.println(user1);
		// �ڶ��η������󣬲�ѯidΪ1���û�
		User user2 = mapper.findUserById(1);  // û�з���sql���
		System.out.println(user2);
		sqlSession.close();
	}
	@Test
	public void testL1Cache2() throws Exception{
		/*
		 * ������β�ѯ֮����commit��sqlSession�����hashMap�еĶ��� 
		 * 1. ������β�ѯ֮����commit����hashMap�Ķ�����գ���2����ѯ���
		 * 2. ���commit�����������ִ��update����ִ�еڶ��β�ѯ���ڶ��β�ѯ���Ϊ���º�Ľ����
		 * 		�еڶ��β�ѯ�������ΪhashMap�Ĵ洢��user1�����Ѿ������
		 */
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		Demo4_UserMapper mapper = sqlSession.getMapper(Demo4_UserMapper.class);
		// ��һ�η������󣬲�ѯidΪ1���û�
		User user1 = mapper.findUserById(1);
		System.out.println(user1);
		user1.setAddress("not nullaaa!");
		mapper.updateUser(user1);
		// �ڶ��η������󣬲�ѯidΪ1���û�
		User user2 = mapper.findUserById(1);  // û�з���sql���
		System.out.println(user2);
		sqlSession.commit();
		sqlSession.close();
	}
}
