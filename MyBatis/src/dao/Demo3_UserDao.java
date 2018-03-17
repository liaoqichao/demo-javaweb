package dao;

import java.util.List;

import mybatis.po.User;

/**
 * 
 * @author Liaoqichao
 * @date 2016��3��10��
 */
public interface Demo3_UserDao {
	
	/**
	 * ����id��ѯ�û���Ϣ
	 * @param id
	 * @return
	 * @throws Exception
	 */
	User findUserById(int id) throws Exception;
	
	/**
	 * �����û�
	 * @param user
	 * @throws Exception
	 */
	void insertUser(User user) throws Exception;
	
	/**
	 * ����idɾ���û�
	 * @param id
	 * @throws Exception
	 */
	void deleteUser(int id) throws Exception;
	
	/**
	 * �����û�����ģ����ѯ��ȡ�û��б�
	 * @param name
	 * @return
	 * @throws Exception
	 */
	List<User> findUsersByName(String name) throws Exception;
	
	
}
