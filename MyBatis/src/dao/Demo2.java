package dao;

import java.io.IOException;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import mybatis.po.User;

/**
 * 
 * @author Liaoqichao
 * @date 2016年3月9日
 */
public class Demo2 {

	/**
	 * 增
	 * @throws IOException
	 */
//	@Test
	public void testInsertUser() throws IOException{
		/*
		 * 1. 根据主配置文件创建Sqlsession工厂
		 * 2. 根据工厂得到sqlSession
		 * 3. 通过sqlSession操作数据库
		 * 		- insert
		 *  	- commit（）//Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@4d95d2a2]
		 *  		不是自动提交，要手动提交。
		 * 4. 释放资源
		 */
		
		// 1. 
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
		
		// 2. 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 3.
		User user = new User();
		user.setUsername("浪里个浪");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("阿拉拉了");
		sqlSession.insert("test.insertUser", user);
		
		// 提交
		sqlSession.commit();
		
		// 因为主键自增长，所以补知道主键，现在要获取主键。
		System.out.println(user.getId());
		// 4. 
		sqlSession.close();
	}
	
	/**
	 * 删
	 * @throws IOException
	 */
//	@Test
	public void testDeleteUserById() throws IOException{
		/*
		 * 1. 得到sqlSessionFactory
		 * 2. 得到sqlSession
		 * 3. 通过sqlSession操作数据库
		 * 4. 提交事务
		 * 5. 释放资源
		 */
		
		// 1. 
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
		
		// 2. 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 3. 
		sqlSession.delete("test.deleteUserById", 22); //删除 22 陈小明
		
		// 4.
		sqlSession.commit();
		
		// 5. 
		sqlSession.close();
	}
	
	/**
	 * 更新
	 * @throws IOException 
	 */
	@Test
	public void updateUser() throws IOException{
		/*
		 * 1. 得到SqlSessionFactory
		 * 2. 得到SqlSession
		 * 3. 通过sqlSession操作数据库
		 * 4. 提交事务
		 * 5. 释放资源
		 */
		
		// 1. 
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()//
				.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
		
		// 2. 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 3. 
		User user = sqlSession.selectOne("test.findUserById", 26); //修改id为26的，username=王五，其他属性都为null的记录
		System.out.println(user);
		user.setUsername("我更新前叫王五");
		user.setSex("1");
		user.setBirthday(new Date());
		user.setAddress("隔壁");
		
		sqlSession.update("test.updateUser", user);
		
		// 4. 
		sqlSession.commit();
		
		// 5. 
		sqlSession.close();
	}
}
