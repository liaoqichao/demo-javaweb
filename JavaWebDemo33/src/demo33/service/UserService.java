package demo33.service;

import demo33.dao.UserDao;
import demo33.domain.User;

/**
 * ҵ���߼���ֻ�����ݷ��ʲ�(dao)�Ӵ����������ݿ�Ӵ���
 * @author Administrator
 *
 */
public class UserService {

	
//	  service������dao��
	 
	private UserDao userdao = new UserDao();
	
//	service�Ĳ�ѯ��Ҫ����dao�����
	public User find(){
		return userdao.find();
	}
}
