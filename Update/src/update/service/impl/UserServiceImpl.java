package update.service.impl;

import javax.annotation.Resource;

import update.dao.UserDao;
import update.domain.User;
import update.service.UserException;
import update.service.UserService;

//@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	public User login(User form) throws UserException{
		/*
		 * 1.获取用户名和密码
		 * 2.根据用户名查询用户
		 *  > 如果返回为null，error=“用户不存在”
		 *  > 如果返回不为null，下一步
		 * 3.匹配表单的psw和查询到对象的psw
		 *  > 如果一样，返回true，返回到主页
		 *  > 如果不一样，返回false，error="密码错误"，返回到登录页面，回显用户名
		 */
		//2. 
		User user = userDao.findByUsername(form.getUsername());
		if(user == null){
			throw new UserException("用户名不存在！");
		} else {
			if(user.getPsw().equals(form.getPsw())){
				return user;// 0表示输入正确
			}
			else {
				throw new UserException("密码错误");
			}
		}
	}

	@Override
	public void modifyPsw(User form, String new_password) throws UserException {
		/*
		 *  1. 判断账号是否存在
		 * 	2. 判断原密码是否正确
		 * 	3. 如果正确修改密码
		 */
		
		User user = userDao.findByUsername(form.getUsername()); //得到持久化状态对象
		if(user == null){  // 1. 判断账号是否存在
			throw new UserException("用户名不存在！");
		} else { // 2. 判断原密码是否正确
			if(user.getPsw().equals(form.getPsw())){
				// 3. 如果正确修改密码
				user.setPsw(new_password);
			}
			else {
				throw new UserException("密码错误");
			}
		}
	}
}
