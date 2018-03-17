package mapper;

import java.util.List;

import mybatis.po.User;

/**
 * mapper接口，相当于dao接口
 * @author Liaoqichao
 * @date 2016年3月11日
 */
public interface Demo4_UserMapper {

	
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
	void deleteUserById(int id) throws Exception;
	
	/**
	 * 根据用户名称模糊查询获取用户列表
	 * @param name
	 * @return
	 * @throws Exception
	 */
	List<User> findUsersByName(String name) throws Exception;
	
	/**
	 * 更新用户信息
	 * @param user
	 * @throws Exception
	 */
	void updateUser(User user) throws Exception;
}
