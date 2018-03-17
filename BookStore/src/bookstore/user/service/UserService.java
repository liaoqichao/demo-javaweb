package bookstore.user.service;

import bookstore.user.dao.UserDao;
import bookstore.user.domain.User;

/**
 * User业务层
 */
public class UserService {
	private UserDao userDao = new UserDao();
	

	/**
	 * 注册功能
	 * @param form
	 * @throws UserException 
	 */
	public void regiset(User form) throws UserException{
		//1.校验用户名
		User user = userDao.findByUsername(form.getUsername());
		if(user!=null)	throw new UserException("用户名已被注册！");
		//2.校验email
		user = userDao.findByEmail(form.getEmail());
		if(user!=null)	throw new UserException("Email已被注册！");
		//3.能执行到这里说明user为空，把表单的数据插入到数据库中
		userDao.add(form);
	}
	
	/**
	 * 激活业务
	 * @param code
	 * @throws UserException 
	 */
	public void active(String code) throws UserException{
		/*
		 * 1.使用code查询数据库，得到User对象
		 * 	> 如果返回为null，抛出异常
		 * 2.查看用户的状态，时候已经被激活。
		 * 	> true：抛出异常
		 * 	> false:修改用户状态为true
		 */
		//1.
		User user = userDao.findByCode(code);
		if(user == null) throw new UserException("激活码无效");
		//2.
		if(user.isState()) throw new UserException("您已经激活过了！");
		//3.
		userDao.updateState(user.getUid(), true);
		
	}
	
	/**
	 * 登录业务
	 * @param form
	 * @throws UserException 
	 */
	public User login(User form) throws UserException{
		//1.通过form.username找到user，没有则抛出异常（用户不存在）
		User user = userDao.findByUsername(form.getUsername());
		if(user == null ) throw new UserException("用户不存在!");
		//2.比较form.password和user.password，不同则抛出异常（密码错误）
		if(!user.getPassword().equals(form.getPassword())) throw new UserException("密码错误!");
		//3.判断user.state是否为true，否则抛出异常（用户未激活）
		if(!user.isState()) throw new UserException("用户尚未激活!");
		//4.返回查询结果(user)，用来保存到session域
		return user;
	}
}
