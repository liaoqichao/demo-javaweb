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
	 * ��Ӷ�������Ҫ�������񡣷�ֹ�����Ͷ�����Ŀ���뵽���ݿ�ʱֻ����һ����
	 * @param order
	 */
	public void add(Order order){
		try {
			//��������
			JDBCUtils.beginTransaction();
			
			orderDao.add(order);//���붩��
			orderDao.addOrderItemList(order.getOrderItemList());//���붩���е�������Ŀ
			
			//�ύ����
			JDBCUtils.commitTransaction();

//			//��õ�ǰ����������
//			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
//			record(method,order);
		} catch (Exception e) {
			//�ع�����
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException(e);//�쳣��Ϣ����Ҫ��ʾ����Ȼ��Ϊ�ɹ�
		}
	}
	
//	/**
//	 * ��¼��̨����Ա����ɾ�ĵ���־
//	 * @param method
//	 * @param order
//	 */
//	private void record(String method, Object object) {
//		//1.��ȡ�����
//		String path = this.getClass().getClassLoader().getResource("/adminlog.txt").getPath();
//		System.out.println(path);
//		File file = new File(path);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		try {
//			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));//�����true��ʾ������֮ǰ������
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
	 * �ҵĶ���
	 * @param uid
	 * @return
	 */
	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid);
	}

	/**
	 * ���ض���(�ڶ����б�������ʱ)
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		return orderDao.load(oid);
	}
	
	/**
	 * ȷ�϶���
	 * @param oid
	 */
	public void confirm(String oid) throws OrderException{
		/*
		 * 1.У�鶩��״̬���������3���׳��쳣
		 * 2.�޸Ķ���״̬Ϊ4����ʾ���׳ɹ�
		 */
		//1.
		int state = orderDao.getStateByOid(oid);
		if(state != 3) throw new OrderException("ȷ�϶���ʧ�ܣ��㲻��ʲô����");
		//2.
		orderDao.updateState(oid, 4);
		
	}
	
	/**
	 * ֧��ҵ��
	 * @param oid
	 */
	public void pay(String oid){
		/*
		 * 1.��ȡ����״̬
		 * 	> ���״̬Ϊ1��ִ���������
		 *  > ���״̬��Ϊ1��������ʲô������
		 */
		int state = orderDao.getStateByOid(oid);
		if(state == 1){
			//�޸Ķ���״̬Ϊ2
			orderDao.updateState(oid, 2);
			//..�ӻ���
		}
	}

	/**
	 * ��ѯ���ж���
	 */
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	/**
	 * ͨ������״̬��ѯ����
	 * @param state
	 * @return
	 */
	public List<Order> findByState(int state) {
		return orderDao.findByState(state);
	}

	/**
	 * �������޸Ķ���״̬2Ϊ3
	 */
	public void deliver(String oid) {
		/*
		 * 1.��ȡ����״̬
		 * 	> ���״̬Ϊ2��ִ���������
		 *  > ���״̬��Ϊ2��������ʲô������
		 */
		int state = orderDao.getStateByOid(oid);
		if(state == 2){
			//�޸Ķ���״̬Ϊ3
			orderDao.updateState(oid, 3);
			//��õ�ǰ����������
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			Record record = Record.newInstance();
			record.recordToAdminlog(this.getClass().getName(),method, oid);
		}
	}
}
