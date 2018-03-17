package mapper;

import java.util.List;

import mybatis.po.UserCustom;
import mybatis.po.UserQueryVo;

/**
 * demo6
 * @author Liaoqichao
 * @date 2016年3月13日
 */
public interface Demo6_UserMapper {

	/**
	 * 用户信息综合查询
	 * @return
	 * @throws Exception
	 */
	List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;
	
	// 新增
	/**
	 * 查询满足查询条件的记录总数
	 * @param userQueryVo
	 * @return
	 * @throws Exception
	 */
	int findUserCount(UserQueryVo userQueryVo) throws Exception;
}
