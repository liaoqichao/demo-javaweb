package dao;

import java.util.List;

import mybatis.po.User;

/**
 * 
 * @author Liaoqichao
 * @date 2016年3月10日
 */
public interface Demo3_UserDao {
	
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	User findUserById(int id) throws Exception;
	
	/**
	 * 插入用户
	 * @param user
	 * @throws Exception
	 */
	void insertUser(User user) throws Exception;
	
	/**
	 * 根据id删除用户
	 * @param id
	 * @throws Exception
	 */
	void deleteUser(int id) throws Exception;
	
	/**
	 * 根据用户名称模糊查询获取用户列表
	 * @param name
	 * @return
	 * @throws Exception
	 */
	List<User> findUsersByName(String name) throws Exception;
	
	
}
