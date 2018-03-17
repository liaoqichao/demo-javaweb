package bookstore.cart.domain;

import java.math.BigDecimal;

import bookstore.book.domain.Book;

/**
 * ���ﳵ��Ŀ��
 */
public class CartItem {
	private Book book;	//��
	private int count;	//��Ʒ����
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
	 * С�Ʒ����������˶���������������⡣
	 * @return
	 */
	public double getSubtotal(){	//С��
		BigDecimal b1 = new BigDecimal(book.getPrice()+"");//����ַ�������
		BigDecimal b2 = new BigDecimal(count+"");
		return b1.multiply(b2).doubleValue();	//��BigDecimal���ͱ��double���͡�
	}
	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
	
	
}
