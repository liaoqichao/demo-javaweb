package user.service;

import user.dao.DaoFactory;
import user.dao.UserDao;
import user.domain.User;

public class UserService {
	
	//�Ѿ����ʵ����Ĵ������ص�������
	private UserDao userDao = DaoFactory.getUserDao();//Service����Dao
	
	public void regist(User user) throws UserException{
		/*
		 * 1.ʹ���û�����ѯ,�������null,������
		 * 2.������ز���null,�׳��쳣
		 */
		
		//_user��ʾ���Һ󷵻����Ķ��������ݿ�����ݡ�
		User _user = userDao.findByUsername(user.getUsername());
		
		if(_user != null){
			//�׳��쳣
			throw new UserException("�û���"+user.getUsername()+"�Ѿ���ע���ˣ�");
		}
		//1.������
		userDao.addUser(user);
	}

	public User login(User form) throws UserException {
		/*
		 * 1.ʹ��form�е�username���в�ѯ���õ�User user
		 * 2.�������Ϊnull���׳��쳣��������ϢΪ�û���������
		 * 3.�����Ϊnull���Ƚ�form��password��user��password�������ͬ�׳��쳣���������
		 * 4.���������ͬ�򷵻�user��user�����ݿ�����ݣ���Ϣȫ(���֣���Ȥ...)����formֻ��username��password
		 */
		//1.
		User user = userDao.findByUsername(form.getUsername());
		//2.
		if(user==null){
			throw new UserException("�û���������");
		}
		//3.
		if(!form.getPassword().equals(user.getPassword())){
			throw new UserException("�������");
		}
		//4.
		return user;
	}

}
