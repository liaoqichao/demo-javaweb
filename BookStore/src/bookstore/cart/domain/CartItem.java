package bookstore.cart.domain;

import java.math.BigDecimal;

import bookstore.book.domain.Book;

/**
 * 购物车条目类
 */
public class CartItem {
	private Book book;	//书
	private int count;	//商品数量
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * 小计方法，处理了二进制运算误差问题。
	 * @return
	 */
	public double getSubtotal(){	//小计
		BigDecimal b1 = new BigDecimal(book.getPrice()+"");//变成字符串类型
		BigDecimal b2 = new BigDecimal(count+"");
		return b1.multiply(b2).doubleValue();	//从BigDecimal类型变成double类型。
	}
	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
	
	
}
