package mapper;

import java.util.List;

import mybatis.po.UserCustom;
import mybatis.po.UserQueryVo;

/**
 * demo5 �û���Ϣ�ۺϲ�ѯ
 * @author Liaoqichao
 * @date 2016��3��13��
 */
public interface Demo5_UserMapper {

	// ����
	/**
	 * �û���Ϣ�ۺϲ�ѯ
	 * @return
	 * @throws Exception
	 */
	List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;
}
