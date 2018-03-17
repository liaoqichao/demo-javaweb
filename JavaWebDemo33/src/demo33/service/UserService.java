package demo33.service;

import demo33.dao.UserDao;
import demo33.domain.User;

/**
 * 业务逻辑层只和数据访问层(dao)接触。不和数据库接触。
 * @author Administrator
 *
 */
public class UserService {

	
//	  service层依赖dao层
	 
	private UserDao userdao = new UserDao();
	
//	service的查询需要依赖dao来完成
	public User find(){
		return userdao.find();
	}
}
