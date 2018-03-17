package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import mybatis.po.User;

/**
 * 
 * @author Liaoqichao
 * @date 2016年3月8日
 */
public class Demo1 {
	
	/**
	 *  根据id查询用户信息，得到一条记录的结果
	 * @throws IOException
	 */
//	@Test
	public void testFindUserById() throws IOException{
		
		/*
		 * 1. 创建会话工厂
		 * 2. 通过工厂得到SqlSession
		 * 3. 通过SqlSession操作数据库
		 * 4. 释放资源
		 */
		
		// 1. 创建会话工厂,传入主配置文件
		// mybatis主配置文件相对路径
		String resource = "SqlMapConfig.xml";//根目录是src?
		// 获取mybatis主配置文件的输入流
		InputStream inputStream = Resources.getResourceAsStream(resource); // Resources由mybatis提供
		// 得到会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		// 2. 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 3. 通过sqlSession操作数据库
		/*
		 * 第一个参数：映射文件中的statement的id，等于命名空间+statement的id // namespace.statementid
		 * 第二个参数：指定和映射文件中所匹配的parameterType类型的参数.配置文件是int，所以输入1，但是输入字符串"1"也没报错
		 * 返回泛型类型：就是映射文件中所匹配的resultType对应的类型
		 */
		User user = sqlSession.selectOne("test.findUserById", 1);
		System.out.println(user);
		
		// 4. 释放资源
		sqlSession.close(); // 在finlly代码块释放
	}
	
	/**
	 * 根据用户名称模糊查询用户信息，映射文件使用原来的User.xml
	 * @throws IOException 
	 */
	@Test
	public void findUsersByName() throws IOException{
		/*
		 * 1. 创建会话工厂
		 * 2. 通过工厂得到会话
		 * 3. 通过会话操作数据库
		 * 4. 释放资源
		 */
		// 1. 
		InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		
		// 2. 
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// 3. 第一个参数，statement的id，第二个参数，sql模板的参数，模糊查询要添加百分号
		// 使用#{}占位符，生成的sql模板会有?占位符
//		List<User> userList = sqlSession.selectList("demo1.findUsersByName", "%小明%");
		// 使用${}拼接。生成的sql模板没有?占位符
		// 映射文件中使用了'%${value}%'，这样是单纯的字符串拼接，变成'%小明%'，单纯的字符串拼接会引起sql注入，上面是#{}。
		List<User> userList = sqlSession.selectList("test.findUsersByName", "小明");
		
		for (User user : userList) {
			System.out.println(user);
		}
		
		// 4. 
		sqlSession.close();
	}
}
