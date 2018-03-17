package springmybatis.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import springmybatis.dao.UserDao;
import springmybatis.po.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	/*
	 * ����Ҫ����ΪSqlSessionDaoSupport����sqlSessionFactory��getter��setter
	 * SqlSession sqlSession = this.getSqlSession();�����Ϳ��Եõ�sqlSession
	 */
//	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public User findUserById(int id) throws Exception {
		SqlSession sqlSession = this.getSqlSession();
		User user = sqlSession.selectOne("test.findUserById", id);
		return user;
	}

	@Override
	public void insertUser(User user) throws Exception {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.insert("test.insertUser", user);
	}

	@Override
	public void deleteUser(int id) throws Exception {
		SqlSession sqlSession = this.getSqlSession();
		sqlSession.delete("test.deleteUserById", id);
	}

	@Override
	public List<User> findUsersByName(String name) throws Exception {
		SqlSession sqlSession = this.getSqlSession();
		List<User> userList = sqlSession.selectList("test.findUsersByName", name);
		return userList;
	}

}
