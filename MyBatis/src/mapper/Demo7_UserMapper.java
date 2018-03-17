package mapper;

import mybatis.po.User;

/**
 * demo6
 * @author Liaoqichao
 * @date 2016年3月13日
 */
public interface Demo7_UserMapper {
	/**
	 * 根据id查询用户信息。
	 * 其中sql语句中的查询结果使用了别名，所以要用resultMap来将别名和pojo属性名进行映射。
	 * @param id
	 * @return
	 * @throws Exception
	 */
	User findUserByIdResultMap(int id) throws Exception;
}
