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
	 * ��ѯ����ͼ��
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
	 * �������ѯ
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
	 * ͨ��bid<pk>��ѯ��
	 * @param bid
	 * @return
	 */
	public Book load(String bid) {
		try {
			String sql = "SELECT * FROM book WHERE bid=?";
			Map<String,Object> map = qr.query(sql, new MapHandler(),bid);
			Book book = CommonUtils.toBean(map, Book.class);//ȫ����Ϣ����
			Category category = CommonUtils.toBean(map, Category.class);//ֻ��cid
			category = categoryDao.load(category.getCid());//ͨ����ѯ������һ��cid��cname���е�category
/*
 * 			Ϊʲôֻ��cidҳ��Ҳ������ʾcname?��Ϊҳ����ʾ����${category.cname}��categoryList��һ��Ԫ��
 * 			book.categoryֻ������cid�����Ƚϡ������������Ǿ�������ţ���book.category����Ϣ��ȫ��
 */
			book.setCategory(category);//������ϵ
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * ��ѯָ�������µ�ͼ�鱾��
	 * ͨ��cid��ѯ�÷����µ�ͼ�������
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
	 * ���ͼ��
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
	 * ɾ��ͼ�飺���ǰ�ͼ���del = false�޸�Ϊtrue����Ϊ�����������һɾ�ͱ���
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
	 * �޸�ͼ��
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
