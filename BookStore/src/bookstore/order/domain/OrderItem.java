package bookstore.order.domain;

import bookstore.book.domain.Book;

/**
 * 订单条目类
 */
public class OrderItem {

	private String iid;
	private int count;//数量
	private double subtotal;//小计
	private Order order;//所属订单，条目必须是某个订单的。对应数据库的oid
	private Book book;//所要购买的图书，对应数据库的bid
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	@Override
	public String toString() {
		return "OrderItem [iid=" + iid + ", count=" + count + ", subtotal=" + subtotal + ", order=" + order + ", book="
				+ book + "]";
	}
	
}
