package springmybatis.mapper;

import java.util.List;

import springmybatis.po.User;

/**
 * 
 * @author Liaoqichao
 * @date 2016��3��25��
 */
public interface Demo17_UserMapper {

	
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
	void deleteUserById(int id) throws Exception;
	
	/**
	 * �����û�����ģ����ѯ��ȡ�û��б�
	 * @param name
	 * @return
	 * @throws Exception
	 */
	List<User> findUsersByName(String name) throws Exception;
	
	/**
	 * �����û���Ϣ
	 * @param user
	 * @throws Exception
	 */
	void updateUser(User user) throws Exception;
}
