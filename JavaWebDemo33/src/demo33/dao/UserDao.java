package demo33.dao;

import demo33.domain.User;

/**
 * �����ݿ�����ݲ�ѯ����֮��,��װ��User������,Ȼ�󷵻ء�
 * �����User�����ݵ����塣
 * @author Administrator
 *
 */
public class UserDao {
	
	public User find(){
		return new User("����","123");
	}
}
