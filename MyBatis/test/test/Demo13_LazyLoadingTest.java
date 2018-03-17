package test;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import mapper.OrdersCustomMapper;
import mybatis.po.Orders;

public class Demo13_LazyLoadingTest {

	private SqlSessionFactory factory;
	@Before
	public void setUp() throws Exception {
	
		this.factory = new SqlSessionFactoryBuilder().//
				build(Resources.getResourceAsStream("SqlMapConfig.xml"));
	
	}

	@Test
	public void testFindOrdersUserLazyLoading() throws Exception {
		SqlSession sqlSession = factory.openSession();
		OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
		// 1. 执行SELECT * FROM orders
		List<Orders> ordersList = mapper.findOrdersUserLazyLoading();
		if(ordersList!= null){
			if(ordersList.size() != 0){
				// 只是查询订单的信息，没有执行mapper.Demo4_UserMapper.findUserById
				for (Orders orders : ordersList) {
					System.out.println("-------------------------------");
					System.out.println(orders.getId());  
					System.out.println("====================================");
					// 查询用户信息，需要执行mapper.Demo4_UserMapper.findUserById
					// 2. 执行SELECT * FROM `user` WHERE id=1  其中1是根据orders.user_id(外键)获取的。
					System.out.println(orders.getUser().getUsername());
					/*
					 * 遍历分析：
					 *  得到列表：执行了SELECT * FROM orders
					 * 	第一个：订单3，用户王五。执行了SELECT username FROM `user` WHERE id=?
					 *  第二个：订单4，用户王五。没有执行statement。因为一级缓存！！缓存中已经有王五对应的对象(主键?)
					 *  第三个: 订单5，用户张三。执行了SELECT username FROM `user` WHERE id=?
					 */
				}
			}
		}
		sqlSession.close();
	}

}
