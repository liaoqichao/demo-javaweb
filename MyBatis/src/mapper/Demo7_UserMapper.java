package mapper;

import mybatis.po.User;

/**
 * demo6
 * @author Liaoqichao
 * @date 2016��3��13��
 */
public interface Demo7_UserMapper {
	/**
	 * ����id��ѯ�û���Ϣ��
	 * ����sql����еĲ�ѯ���ʹ���˱���������Ҫ��resultMap����������pojo����������ӳ�䡣
	 * @param id
	 * @return
	 * @throws Exception
	 */
	User findUserByIdResultMap(int id) throws Exception;
}
