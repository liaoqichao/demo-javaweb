package domain;

public class Book {
	private String cid;
	private String bname;
	private double price;
	private int category;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
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
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Book [cid=" + cid + ", bname=" + bname + ", price=" + price + ", category=" + category + "]";
	}
	
	
}
