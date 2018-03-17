package mapper;

import java.util.List;

import mybatis.po.UserCustom;
import mybatis.po.UserQueryVo;

/**
 * demo6
 * @author Liaoqichao
 * @date 2016��3��13��
 */
public interface Demo8_UserMapper {

	/**
	 * �û���Ϣ�ۺϲ�ѯ
	 * @param userQueryVo
	 * @return
	 * @throws Exception
	 */
	List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;
	
	/**
	 * �û���Ϣ�ۺϲ�ѯ����
	 * @param userQueryVo
	 * @return
	 * @throws Exception
	 */
	int findUserCount(UserQueryVo userQueryVo) throws Exception;
}
