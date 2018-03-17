package bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bookstore.book.domain.Book;
import bookstore.category.dao.CategoryDao;
import bookstore.category.domain.Category;
import lqcUtils.CommonUtils;
import lqcUtils.db.TxQueryRunner;

public class BookDao {

	private QueryRunner qr = new TxQueryRunner();
	private CategoryDao categoryDao = new CategoryDao();
	
	
	/**
	 * 查询所有图书
	 */
	public List<Book> findAll(){
		try {
			String sql = "SELECT * FROM book WHERE del=false";
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 按分类查询
	 * @param cid
	 * @return
	 */
	public List<Book> findByCategory(String cid) {
		try {
			String sql = "SELECT * FROM book WHERE cid=? AND del=false";
			return qr.query(sql, new BeanListHandler<Book>(Book.class),cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 通过bid<pk>查询书
	 * @param bid
	 * @return
	 */
	public Book load(String bid) {
		try {
			String sql = "SELECT * FROM book WHERE bid=?";
			Map<String,Object> map = qr.query(sql, new MapHandler(),bid);
			Book book = CommonUtils.toBean(map, Book.class);//全部信息都有
			Category category = CommonUtils.toBean(map, Category.class);//只有cid
			category = categoryDao.load(category.getCid());//通过查询，返回一个cid和cname都有的category
/*
 * 			为什么只有cid页面也可以显示cname?因为页面显示的是${category.cname}是categoryList的一个元素
 * 			book.category只用它的cid用来比较。，所以上面那句最好留着，让book.category的信息齐全。
 */
			book.setCategory(category);//产生关系
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 查询指定分类下的图书本数
	 * 通过cid查询该分类下的图书的数量
	 * @param cid
	 * @return
	 */
	public int getCountByCid(String cid) {
		try {
			String sql = "SELECT COUNT(*) FROM book WHERE cid=? AND del=false";
			Number count = (Number)qr.query(sql, new ScalarHandler(), cid);
			return count.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 添加图书
	 * @param book
	 */
	public void add(Book book) {
		try {
			String sql = "INSERT INTO book VALUES(?,?,?,?,?,?,false)";
			Object[] params = {book.getBid(), book.getBname(), book.getPrice(),
					book.getAuthor(), book.getImage(), book.getCategory().getCid()};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 删除图书：就是把图书的del = false修改为true。因为有外键关联，一删就报错。
	 * @param bid
	 */
	public void delete(String bid){
		try {
			String sql = "UPDATE book SET del=true WHERE bid=?";
			qr.update(sql, bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 修改图书
	 * @param book
	 */
	public void modify(Book book) {
		try {
			String sql = "UPDATE book SET bname=?,price=?,author=?,image=?,cid=? WHERE bid=?";
			Object[] params = {book.getBname(), book.getPrice(), book.getAuthor(), book.getImage(),
					book.getCategory().getCid(), book.getBid()};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
