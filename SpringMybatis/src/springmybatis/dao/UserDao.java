package springmybatis.dao;

import java.util.List;

import springmybatis.po.User;

public interface UserDao {

	
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