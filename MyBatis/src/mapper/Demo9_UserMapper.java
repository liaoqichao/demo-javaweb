package mapper;

import java.util.List;

import mybatis.po.UserCustom;
import mybatis.po.UserQueryVo;

/**
 * demo6
 * @author Liaoqichao
 * @date 2016年3月13日
 */
public interface Demo9_UserMapper {

	/**
	 * 用户信息综合查询
	 * @param userQueryVo
	 * @return
	 * @throws Exception
	 */
	List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;

}
