package bookstore.order.domain;

import bookstore.book.domain.Book;

/**
 * ������Ŀ��
 */
public class OrderItem {

	private String iid;
	private int count;//����
	private double subtotal;//С��
	private Order order;//������������Ŀ������ĳ�������ġ���Ӧ���ݿ��oid
	private Book book;//��Ҫ�����ͼ�飬��Ӧ���ݿ��bid
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
