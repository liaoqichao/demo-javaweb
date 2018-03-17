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
	 * 添加订单
	 * @param order
	 */
	public void add(Order order){
		try {
			String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?)";
			/*
			 * 处理util的Date转换到sql的TimeStamp//包括如期和时间
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
	 * 在订单条目表插入订单条目，这里需要批处理。
	 * @param orderItemList
	 */
	public void addOrderItemList(List<OrderItem> orderItemList){
		try {
			/*
			 * QueryRunner的batch(String sql, Object[][] params)
			 * 其中params是多个一维数组，每个一维数组都与sql在一起执行一次。多个一维数组就执行多次。
			 */
			String sql = "INSERT INTO orderItem VALUES(?,?,?,?,?)";
			/*
			 * 把orderItemList转换为二维数组。
			 */
			Object[][] params = new Object[orderItemList.size()][];
			//循环遍历orderItemList，使用每个orderItem对象给每个params中的一维数组赋值
			for (int i = 0; i < orderItemList.size(); i++) {
				OrderItem orderItem = orderItemList.get(i);
				params[i] = new Object[] {orderItem.getIid(), orderItem.getCount(),
						orderItem.getSubtotal(), orderItem.getOrder().getOid(),
						orderItem.getBook().getBid()};
			}
			
			qr.batch(sql, params);//执行批处理
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过uid查询这个用户的所有订单
	 * @param uid
	 * @return
	 */
	public List<Order> findByUid(String uid) {
		/*
		 * 1.通过uid查询当前用户的所有订单。List<Order>
		 * 2.循环遍历每个Order，为其加载它的所有OrderItem
		 * 3.返回订单列表
		 */
		try {
			//1.得到当前用户的所有订单
			String sql = "SELECT * FROM orders WHERE uid=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class),uid);
			//2.循环遍历每个Order，为其加载它自己的所有的订单条目。
			for (Order order : orderList) {
				loadOrderItems(order);//为order对象加载所有的订单条目
			}
			//3.
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加载指定的订单的所有订单条目
	 * @param order
	 */
	private void loadOrderItems(Order order) {
		try {
			//多表查询（orderitem，book），去除笛卡尔积
			String sql = "SELECT * FROM orderitem i ,book b WHERE i.bid=b.bid and oid=?";
			//只要是不能完全映射的都用MapHandler或者MapListHandler
//			因为结果集对应的不是一个javabean所以用MapHandler或者MapListHandler,然后用toBean把一个结果集变成几个类
			List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
			/*
			 * 每个map对应一行结果集，我们需要让一个Map生成2个对象(orderitem,book),然后再建立两者的关系(orderitem.setBook(book))
			 */
			List<OrderItem> orderItemList = toOrderItemList(mapList);
			order.setOrderItemList(orderItemList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 循环遍历把mapList中的每个Map转换成orderitem和book，然后两个对象建立关系
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
	 * 把一个map转换成一个orderitem对象，一个book对象，并建立联系
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
	 * 加载订单
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		try {
			//1.得到订单
			String sql = "SELECT * FROM orders WHERE oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
			//2.给订单添加List<OrderItem>
			loadOrderItems(order);////加载指定的订单的所有订单条目
			//3.返回订单
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 通过oid查询订单状态
	 * @param oid
	 * @return 订单状态(int)
	 */
	public int getStateByOid(String oid){
		try {
			String sql = "SELECT state FROM orders WHERE oid=?";
			/*
			 * 如果查询的是count(*)聚合函数查询出来的，本身表里面不存在的，用Number
			 * 如果是state这些本身就存在在表里面，可以不用Number，直接用Integer或者int
			 */
			Number num = (Number)qr.query(sql, new ScalarHandler(), oid);
			return num.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 修改订单状态
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
	 * 查询所有订单
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
	 * 给订单设置Owner
	 * @param order
	 */
	private void loadUser(Order order) {
		try {
			//多表查询去除笛卡尔积
			String sql = "SELECT * FROM orders o,tb_user u WHERE o.uid=u.uid";
			List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
			for (Map<String, Object> map : mapList) {	
				//得到的map可以封装成order和user，但是order已经有了，就差user，所以只用获取user
				User owner = CommonUtils.toBean(map, User.class);
				order.setOwner(owner);//设置User
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过订单状态查询订单
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
