package user.service;

import user.dao.DaoFactory;
import user.dao.UserDao;
import user.domain.User;

public class UserService {
	
	//把具体的实现类的创建隐藏到工厂中
	private UserDao userDao = DaoFactory.getUserDao();//Service依赖Dao
	
	public void regist(User user) throws UserException{
		/*
		 * 1.使用用户名查询,如果返回null,完成添加
		 * 2.如果返回不是null,抛出异常
		 */
		
		//_user表示查找后返回来的对象是数据库的数据。
		User _user = userDao.findByUsername(user.getUsername());
		
		if(_user != null){
			//抛出异常
			throw new UserException("用户名"+user.getUsername()+"已经被注册了！");
		}
		//1.完成添加
		userDao.addUser(user);
	}

	public User login(User form) throws UserException {
		/*
		 * 1.使用form中的username进行查询，得到User user
		 * 2.如果返回为null，抛出异常，错误信息为用户名不存在
		 * 3.如果不为null，比较form的password和user的password。如果不同抛出异常（密码错误）
		 * 4.如果密码相同则返回user。user是数据库的数据，信息全(积分，兴趣...)，而form只有username和password
		 */
		//1.
		User user = userDao.findByUsername(form.getUsername());
		//2.
		if(user==null){
			throw new UserException("用户名不存在");
		}
		//3.
		if(!form.getPassword().equals(user.getPassword())){
			throw new UserException("密码错误");
		}
		//4.
		return user;
	}

}
