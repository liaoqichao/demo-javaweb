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
import mybatis.po.OrdersCustom;

public class Demo10_OrdersCustomMapperTest {

	private SqlSessionFactory factory;
	@Before
	public void setUp() throws Exception {
	
		this.factory = new SqlSessionFactoryBuilder().//
				build(Resources.getResourceAsStream("SqlMapConfig.xml"));
	
	}

//	@Test
	public void testFindOrdersUser() throws Exception {
		SqlSession sqlSession = factory.openSession();
		OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
		List<OrdersCustom> OrdersCustomList = mapper.findOrdersUser();
		for (OrdersCustom ordersCustom : OrdersCustomList) {
			System.out.println(ordersCustom);
		}
		sqlSession.close();
	}
	@Test
	public void testFindOrdersUserResultMap() throws Exception {
		SqlSession sqlSession = factory.openSession();
		OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
		List<Orders> OrdersCustomList = mapper.findOrdersUserResultMap();
		for (Orders orders : OrdersCustomList) {
			System.out.println(orders.getNumber() + ":" + orders.getUser().getUsername());
		}
		sqlSession.close();
	}

}
