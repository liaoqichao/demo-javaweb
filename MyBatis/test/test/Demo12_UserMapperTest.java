package test;

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
import mybatis.po.User;

public class Demo12_UserMapperTest {

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
		List<User> userList = mapper.findUserAndItemsResultMap();
		System.out.println(userList.size());
		for (User user : userList) {
			System.out.println(user);
			for (Orders order : user.getOrdersList()) {
				for (Orderdetail orderdetail : order.getOrderdetails()) {
					System.out.println(orderdetail.getItems());
				}
			}
			System.out.println("========================");
		}
	}

}
