package demo33.dao;

import demo33.domain.User;

/**
 * 把数据库的数据查询出来之后,封装到User对象中,然后返回。
 * 这里的User是数据的载体。
 * @author Administrator
 *
 */
public class UserDao {
	
	public User find(){
		return new User("张三","123");
	}
}
