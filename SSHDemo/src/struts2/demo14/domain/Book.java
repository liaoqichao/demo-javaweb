package struts2.demo14.domain;

/**
 * ��ʾ��3��Book����ŵ�List�У���ȡList���Ӽ��������Ӽ�������򣺼۸����60
 * ʹ��OGNL���ʽ��ȡ�����Ӽ�
 */
public class Book {
	private String bid;
	private String bname;
	private double price;
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", price=" + price + "]";
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String bid, String bname, double price) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.price = price;
	}
	
	
}
