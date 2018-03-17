package bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import bookstore.admin.Record;
import bookstore.order.dao.OrderDao;
import bookstore.order.domain.Order;
import lqcUtils.db.JDBCUtils;

public class OrderService {
	private OrderDao orderDao = new OrderDao();

	/**
	 * 添加订单，需要处理事务。防止订单和订单条目插入到数据库时只插入一个。
	 * @param order
	 */
	public void add(Order order){
		try {
			//开启事务
			JDBCUtils.beginTransaction();
			
			orderDao.add(order);//插入订单
			orderDao.addOrderItemList(order.getOrderItemList());//插入订单中的所有条目
			
			//提交事务
			JDBCUtils.commitTransaction();

//			//获得当前方法的名称
//			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
//			record(method,order);
		} catch (Exception e) {
			//回滚事务
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException(e);//异常信息还是要显示，不然以为成功
		}
	}
	
//	/**
//	 * 记录后台管理员的增删改到日志
//	 * @param method
//	 * @param order
//	 */
//	private void record(String method, Object object) {
//		//1.获取输出流
//		String path = this.getClass().getClassLoader().getResource("/adminlog.txt").getPath();
//		System.out.println(path);
//		File file = new File(path);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		try {
//			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));//这里的true表示不覆盖之前的内容
//			PrintWriter pw = new PrintWriter(bw,true);
//			String str = method+"_"+object+"_"+sdf.format(new Date());
//			pw.println(str);
//			pw.close();
//			System.out.println(str);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 我的订单
	 * @param uid
	 * @return
	 */
	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid);
	}

	/**
	 * 加载订单(在订单列表单级付款时)
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		return orderDao.load(oid);
	}
	
	/**
	 * 确认订单
	 * @param oid
	 */
	public void confirm(String oid) throws OrderException{
		/*
		 * 1.校验订单状态，如果不是3，抛出异常
		 * 2.修改订单状态为4，表示交易成功
		 */
		//1.
		int state = orderDao.getStateByOid(oid);
		if(state != 3) throw new OrderException("确认订单失败，你不是什么好人");
		//2.
		orderDao.updateState(oid, 4);
		
	}
	
	/**
	 * 支付业务
	 * @param oid
	 */
	public void pay(String oid){
		/*
		 * 1.获取订单状态
		 * 	> 如果状态为1，执行下面代码
		 *  > 如果状态不为1，本方法什么都不做
		 */
		int state = orderDao.getStateByOid(oid);
		if(state == 1){
			//修改订单状态为2
			orderDao.updateState(oid, 2);
			//..加积分
		}
	}

	/**
	 * 查询所有订单
	 */
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	/**
	 * 通过订单状态查询订单
	 * @param state
	 * @return
	 */
	public List<Order> findByState(int state) {
		return orderDao.findByState(state);
	}

	/**
	 * 发货，修改订单状态2为3
	 */
	public void deliver(String oid) {
		/*
		 * 1.获取订单状态
		 * 	> 如果状态为2，执行下面代码
		 *  > 如果状态不为2，本方法什么都不做
		 */
		int state = orderDao.getStateByOid(oid);
		if(state == 2){
			//修改订单状态为3
			orderDao.updateState(oid, 3);
			//获得当前方法的名称
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			Record record = Record.newInstance();
			record.recordToAdminlog(this.getClass().getName(),method, oid);
		}
	}
}
