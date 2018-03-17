package mapper;

import java.util.List;

import mybatis.po.Orders;
import mybatis.po.OrdersCustom;
import mybatis.po.User;

/**
 * ������Mapper
 * @author Liaoqichao
 * @date 2016��3��17��
 */
public interface OrdersCustomMapper {

	/**
	 * ��ѯ������������ѯ�û���ʹ��ResultType
	 */
	public List<OrdersCustom> findOrdersUser() throws Exception;
	
	/**
	 * ��ѯ������������ѯ�û���ʹ��ResultMap��ʽ
	 * ����ʹ��Orders����Ϊ����ResultMap��ʱ��typeд����Orders
	 */
	public List<Orders> findOrdersUserResultMap() throws Exception;
	
	/**
	 * ��ѯ������������ѯ�û��������û��Ա��û���ַ(һ��һ)��������ѯ������ϸ(һ�Զ�)
	 * @return
	 * @throws Exception
	 */
	public List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;
	
	/**
	 * ��ѯ�û����û��������Ʒ
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserAndItemsResultMap() throws Exception;
	
	/**
	 * ��ѯ������������ѯ�û��������û����ӳټ��ء�
	 * @return
	 * @throws Exception
	 */
	public List<Orders> findOrdersUserLazyLoading() throws Exception;
}
