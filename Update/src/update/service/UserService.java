package update.service;

import update.domain.User;

public interface UserService {
	/**
	 * 返回0表示登录成功
	 * 返回1表示用户不存在
	 * 返回2表示密码错误
	 */
	User login(User form) throws UserException;

	/**
	 * 修改密码
	 */
	void modifyPsw(User form, String new_password)throws UserException;

}