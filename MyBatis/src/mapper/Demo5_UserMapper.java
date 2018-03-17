package mapper;

import java.util.List;

import mybatis.po.UserCustom;
import mybatis.po.UserQueryVo;

/**
 * demo5 用户信息综合查询
 * @author Liaoqichao
 * @date 2016年3月13日
 */
public interface Demo5_UserMapper {

	// 新增
	/**
	 * 用户信息综合查询
	 * @return
	 * @throws Exception
	 */
	List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;
}
