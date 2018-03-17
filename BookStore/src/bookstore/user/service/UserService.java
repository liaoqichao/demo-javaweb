package bookstore.user.service;

import bookstore.user.dao.UserDao;
import bookstore.user.domain.User;

/**
 * Userҵ���
 */
public class UserService {
	private UserDao userDao = new UserDao();
	

	/**
	 * ע�Ṧ��
	 * @param form
	 * @throws UserException 
	 */
	public void regiset(User form) throws UserException{
		//1.У���û���
		User user = userDao.findByUsername(form.getUsername());
		if(user!=null)	throw new UserException("�û����ѱ�ע�ᣡ");
		//2.У��email
		user = userDao.findByEmail(form.getEmail());
		if(user!=null)	throw new UserException("Email�ѱ�ע�ᣡ");
		//3.��ִ�е�����˵��userΪ�գ��ѱ������ݲ��뵽���ݿ���
		userDao.add(form);
	}
	
	/**
	 * ����ҵ��
	 * @param code
	 * @throws UserException 
	 */
	public void active(String code) throws UserException{
		/*
		 * 1.ʹ��code��ѯ���ݿ⣬�õ�User����
		 * 	> �������Ϊnull���׳��쳣
		 * 2.�鿴�û���״̬��ʱ���Ѿ������
		 * 	> true���׳��쳣
		 * 	> false:�޸��û�״̬Ϊtrue
		 */
		//1.
		User user = userDao.findByCode(code);
		if(user == null) throw new UserException("��������Ч");
		//2.
		if(user.isState()) throw new UserException("���Ѿ�������ˣ�");
		//3.
		userDao.updateState(user.getUid(), true);
		
	}
	
	/**
	 * ��¼ҵ��
	 * @param form
	 * @throws UserException 
	 */
	public User login(User form) throws UserException{
		//1.ͨ��form.username�ҵ�user��û�����׳��쳣���û������ڣ�
		User user = userDao.findByUsername(form.getUsername());
		if(user == null ) throw new UserException("�û�������!");
		//2.�Ƚ�form.password��user.password����ͬ���׳��쳣���������
		if(!user.getPassword().equals(form.getPassword())) throw new UserException("�������!");
		//3.�ж�user.state�Ƿ�Ϊtrue�������׳��쳣���û�δ���
		if(!user.isState()) throw new UserException("�û���δ����!");
		//4.���ز�ѯ���(user)���������浽session��
		return user;
	}
}
