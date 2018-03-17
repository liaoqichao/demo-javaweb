package bookstore.book.service;

import java.util.List;

import bookstore.admin.Record;
import bookstore.book.dao.BookDao;
import bookstore.book.domain.Book;

public class BookService {

	private BookDao bookDao = new BookDao();
	
	public List<Book> findAll(){
		return bookDao.findAll();
	}

	public List<Book> findByCategory(String cid) {
		return bookDao.findByCategory(cid);
	}

	public Book load(String bid) {
		return bookDao.load(bid);
	}

	/**
	 * 添加图书
	 * @param book
	 */
	public void add(Book book) {
		bookDao.add(book);
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		Record record = Record.newInstance();
		record.recordToAdminlog(this.getClass().getName(),method, book);
	}
	
	/**
	 * 删除图书
	 * @param bid
	 */
	public void delete(String bid){
		bookDao.delete(bid);
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		Record record = Record.newInstance();
		record.recordToAdminlog(this.getClass().getName(),method, bid);
	}

	/**
	 * 修改图书
	 * @param book
	 */
	public void modify(Book book) {
		bookDao.modify(book);
		String method = Thread.currentThread().getStackTrace()[1].getMethodName();
		Record record = Record.newInstance();
		record.recordToAdminlog(this.getClass().getName(),method, book);
	}
}
