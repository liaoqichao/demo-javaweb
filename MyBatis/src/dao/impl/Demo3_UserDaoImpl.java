package dao.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dao.Demo3_UserDao;
import mybatis.po.User;

/**
 * 
 * @author Liaoqichao
 * @date 2016年3月10日
 */
public class Demo3_UserDaoImpl implements Demo3_UserDao {

	private static SqlSessionFactory sqlSessionFactory;
	static{
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder()//
					.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
		} catch (IOException e) {
			System.err.println("sqlSessionFactory创建出现异常！");
			e.printStackTrace();
		} 
	}
	
	@Override
	public User findUserById(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("test.findUserById", id);
		sqlSession.close();
		return user;
	}

	@Override
	public void insertUser(User user) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		sqlSession.insert("test.insertUser", user);
		sqlSession.commit();
		sqlSession.close();

	}

	@Override
	public void deleteUser(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		sqlSession.delete("test.deleteUserById", id);
		sqlSession.commit();
		sqlSession.close();
	}

	@Override
	public List<User> findUsersByName(String name) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<User> userList = sqlSession.selectList("test.findUsersByName", name);
		sqlSession.close();
		return userList;
	}

}
