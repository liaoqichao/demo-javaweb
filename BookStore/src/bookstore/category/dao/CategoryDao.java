package bookstore.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bookstore.category.domain.Category;
import lqcUtils.db.TxQueryRunner;

public class CategoryDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * ��ѯ���з���
	 * @return
	 */
	public List<Category> findAll() {
		String sql = "SELECT * FROM category";
		try {
			return qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��ӷ���
	 * @param category
	 */
	public void add(Category category) {
		try {
			String sql = "INSERT INTO category VALUES(?,?)";
			qr.update(sql, category.getCid(),category.getCname());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ͨ���������ֲ�ѯ�÷���
	 * @param cname
	 * @return
	 */
	public Category findByCname(String cname){
		try {
			String sql = "SELECT * FROM category WHERE cname=?";
			return qr.query(sql, new BeanHandler<Category>(Category.class), cname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ɾ���÷���
	 * @param cid
	 */
	public void delete(String cid) {
		try {
			String sql = "DELETE FROM category WHERE cid=?";
			qr.update(sql, cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * ͨ��cid��ѯcategory
	 * @param cid
	 * @return
	 */
	public Category load(String cid) {
		try {
			String sql = "SELECT * FROM category WHERE cid=?";
			return qr.query(sql, new BeanHandler<Category>(Category.class), cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * �޸ķ�������
	 * @param category
	 */
	public void modify(Category category) {
		try {
			String sql ="UPDATE category SET cname=? WHERE cid=?";
			qr.update(sql, category.getCname(),category.getCid());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
