package test;

import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import mapper.OrdersCustomMapper;
import mybatis.po.Orderdetail;
import mybatis.po.Orders;

public class Demo11_OrdersMapperTest {

	private SqlSessionFactory factory;
	@Before
	public void setUp() throws Exception {
	
		this.factory = new SqlSessionFactoryBuilder().//
				build(Resources.getResourceAsStream("SqlMapConfig.xml"));
	
	}

	@Test
	public void testFindOrdersAndOrderDetailResultMap() throws Exception {
		SqlSession sqlSession = factory.openSession();
		OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
		List<Orders> ordersList = mapper.findOrdersAndOrderDetailResultMap();
		System.out.println(ordersList.size());
		for (Orders orders : ordersList) {
			System.out.println(orders.getId()+" : "+orders.getUser().getUsername());
//				System.out.println(orderdetail.getItemsId()+" - " + orderdetail.getItemsNum());
			for(Iterator<Orderdetail> it = orders.getOrderdetails().iterator() ; it.hasNext();){
				Orderdetail orderdetail = it.next();
				System.out.println(orderdetail);
			}
			System.out.println("================================================");
		}
	}

}
