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
		// 1. ִ��SELECT * FROM orders
		List<Orders> ordersList = mapper.findOrdersUserLazyLoading();
		if(ordersList!= null){
			if(ordersList.size() != 0){
				// ֻ�ǲ�ѯ��������Ϣ��û��ִ��mapper.Demo4_UserMapper.findUserById
				for (Orders orders : ordersList) {
					System.out.println("-------------------------------");
					System.out.println(orders.getId());  
					System.out.println("====================================");
					// ��ѯ�û���Ϣ����Ҫִ��mapper.Demo4_UserMapper.findUserById
					// 2. ִ��SELECT * FROM `user` WHERE id=1  ����1�Ǹ���orders.user_id(���)��ȡ�ġ�
					System.out.println(orders.getUser().getUsername());
					/*
					 * ����������
					 *  �õ��б�ִ����SELECT * FROM orders
					 * 	��һ��������3���û����塣ִ����SELECT username FROM `user` WHERE id=?
					 *  �ڶ���������4���û����塣û��ִ��statement����Ϊһ�����棡���������Ѿ��������Ӧ�Ķ���(����?)
					 *  ������: ����5���û�������ִ����SELECT username FROM `user` WHERE id=?
					 */
				}
			}
		}
		sqlSession.close();
	}

}
