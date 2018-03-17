package bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bookstore.book.domain.Book;
import bookstore.order.domain.Order;
import bookstore.order.domain.OrderItem;
import bookstore.user.domain.User;
import lqcUtils.CommonUtils;
import lqcUtils.db.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * ��Ӷ���
	 * @param order
	 */
	public void add(Order order){
		try {
			String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?)";
			/*
			 * ����util��Dateת����sql��TimeStamp//�������ں�ʱ��
			 */
			Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
			Object[] params = {order.getOid(), timestamp, order.getTotal(), order.getState(),
					order.getOwner().getUid(), order.getAddress()};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �ڶ�����Ŀ����붩����Ŀ��������Ҫ������
	 * @param orderItemList
	 */
	public void addOrderItemList(List<OrderItem> orderItemList){
		try {
			/*
			 * QueryRunner��batch(String sql, Object[][] params)
			 * ����params�Ƕ��һά���飬ÿ��һά���鶼��sql��һ��ִ��һ�Ρ����һά�����ִ�ж�Ρ�
			 */
			String sql = "INSERT INTO orderItem VALUES(?,?,?,?,?)";
			/*
			 * ��orderItemListת��Ϊ��ά���顣
			 */
			Object[][] params = new Object[orderItemList.size()][];
			//ѭ������orderItemList��ʹ��ÿ��orderItem�����ÿ��params�е�һά���鸳ֵ
			for (int i = 0; i < orderItemList.size(); i++) {
				OrderItem orderItem = orderItemList.get(i);
				params[i] = new Object[] {orderItem.getIid(), orderItem.getCount(),
						orderItem.getSubtotal(), orderItem.getOrder().getOid(),
						orderItem.getBook().getBid()};
			}
			
			qr.batch(sql, params);//ִ��������
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ͨ��uid��ѯ����û������ж���
	 * @param uid
	 * @return
	 */
	public List<Order> findByUid(String uid) {
		/*
		 * 1.ͨ��uid��ѯ��ǰ�û������ж�����List<Order>
		 * 2.ѭ������ÿ��Order��Ϊ�������������OrderItem
		 * 3.���ض����б�
		 */
		try {
			//1.�õ���ǰ�û������ж���
			String sql = "SELECT * FROM orders WHERE uid=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class),uid);
			//2.ѭ������ÿ��Order��Ϊ��������Լ������еĶ�����Ŀ��
			for (Order order : orderList) {
				loadOrderItems(order);//Ϊorder����������еĶ�����Ŀ
			}
			//3.
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ����ָ���Ķ��������ж�����Ŀ
	 * @param order
	 */
	private void loadOrderItems(Order order) {
		try {
			//����ѯ��orderitem��book����ȥ���ѿ�����
			String sql = "SELECT * FROM orderitem i ,book b WHERE i.bid=b.bid and oid=?";
			//ֻҪ�ǲ�����ȫӳ��Ķ���MapHandler����MapListHandler
//			��Ϊ�������Ӧ�Ĳ���һ��javabean������MapHandler����MapListHandler,Ȼ����toBean��һ���������ɼ�����
			List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
			/*
			 * ÿ��map��Ӧһ�н������������Ҫ��һ��Map����2������(orderitem,book),Ȼ���ٽ������ߵĹ�ϵ(orderitem.setBook(book))
			 */
			List<OrderItem> orderItemList = toOrderItemList(mapList);
			order.setOrderItemList(orderItemList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ѭ��������mapList�е�ÿ��Mapת����orderitem��book��Ȼ��������������ϵ
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for (Map<String, Object> map : mapList) {
			OrderItem orderItem = toOrderItem(map);
			orderItemList.add(orderItem);
		}
		return orderItemList;
	}

	/**
	 * ��һ��mapת����һ��orderitem����һ��book���󣬲�������ϵ
	 * @param map
	 * @return
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}

	/**
	 * ���ض���
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		try {
			//1.�õ�����
			String sql = "SELECT * FROM orders WHERE oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
			//2.���������List<OrderItem>
			loadOrderItems(order);////����ָ���Ķ��������ж�����Ŀ
			//3.���ض���
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ͨ��oid��ѯ����״̬
	 * @param oid
	 * @return ����״̬(int)
	 */
	public int getStateByOid(String oid){
		try {
			String sql = "SELECT state FROM orders WHERE oid=?";
			/*
			 * �����ѯ����count(*)�ۺϺ�����ѯ�����ģ���������治���ڵģ���Number
			 * �����state��Щ����ʹ����ڱ����棬���Բ���Number��ֱ����Integer����int
			 */
			Number num = (Number)qr.query(sql, new ScalarHandler(), oid);
			return num.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �޸Ķ���״̬
	 * @param oid
	 * @param state
	 */
	public void updateState(String oid,int state){
		try {
			String sql = "UPDATE orders SET state=? WHERE oid=?";
			Object[] params = {state,oid};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��ѯ���ж���
	 * @return
	 */
	public List<Order> findAll() {
		try {
			String sql = "SELECT * FROM orders";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class));
//			String sql = "SELECT u.*,o.*,i.* FROM tb_user u,orders o,orderitem i WHERE u.uid=o.uid AND i.oid=o.oid";
//			List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
			for (Order order : orderList) {
				loadOrderItems(order);
				loadUser(order);
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ����������Owner
	 * @param order
	 */
	private void loadUser(Order order) {
		try {
			//����ѯȥ���ѿ�����
			String sql = "SELECT * FROM orders o,tb_user u WHERE o.uid=u.uid";
			List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
			for (Map<String, Object> map : mapList) {	
				//�õ���map���Է�װ��order��user������order�Ѿ����ˣ��Ͳ�user������ֻ�û�ȡuser
				User owner = CommonUtils.toBean(map, User.class);
				order.setOwner(owner);//����User
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ͨ������״̬��ѯ����
	 * @param state
	 * @return
	 */
	public List<Order> findByState(int state) {
		try {
			String sql = "SELECT * FROM orders WHERE state=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class),state);
//			String sql = "SELECT u.*,o.*,i.* FROM tb_user u,orders o,orderitem i WHERE u.uid=o.uid AND i.oid=o.oid";
//			List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
			for (Order order : orderList) {
				loadOrderItems(order);
				loadUser(order);
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
