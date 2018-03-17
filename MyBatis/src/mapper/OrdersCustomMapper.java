package mapper;

import java.util.List;

import mybatis.po.Orders;
import mybatis.po.OrdersCustom;
import mybatis.po.User;

/**
 * 订单的Mapper
 * @author Liaoqichao
 * @date 2016年3月17日
 */
public interface OrdersCustomMapper {

	/**
	 * 查询订单，关联查询用户。使用ResultType
	 */
	public List<OrdersCustom> findOrdersUser() throws Exception;
	
	/**
	 * 查询订单，关联查询用户。使用ResultMap方式
	 * 泛型使用Orders，因为定义ResultMap的时候type写的是Orders
	 */
	public List<Orders> findOrdersUserResultMap() throws Exception;
	
	/**
	 * 查询订单，关联查询用户姓名，用户性别，用户地址(一对一)，关联查询订单明细(一对多)
	 * @return
	 * @throws Exception
	 */
	public List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;
	
	/**
	 * 查询用户及用户购买的商品
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserAndItemsResultMap() throws Exception;
	
	/**
	 * 查询订单，关联查询用户。其中用户是延迟加载。
	 * @return
	 * @throws Exception
	 */
	public List<Orders> findOrdersUserLazyLoading() throws Exception;
}
