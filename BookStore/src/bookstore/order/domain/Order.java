package bookstore.order.domain;

import java.util.Date;
import java.util.List;

import bookstore.user.domain.User;

/**
 * 订单类。
 * 订单类和订单条目类双向关联。
 */
public class Order {

	private String oid;
	private Date ordertime;//下单时间。Date是util类型，只有dao能使用sql的东西。
	private double total; //合计
	private int state;//订单状态有4种：1未付款，2已付款未发货，3已发货未确认，4.已确认交易成功。一个订单刚生成是未付款状态(默认值是1)。
	private User owner;//订单所有者，对应数据库的uid
	private String address;//收货地址
	
	private List<OrderItem> orderItemList;	//双向关联。当前订单下所有条目
	
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state + ", owner="
				+ owner + ", address=" + address + "]";
	}
	
	
}
