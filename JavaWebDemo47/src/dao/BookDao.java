package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import domain.Book;
import lqcUtils.db.JDBCUtils;
import lqcUtils.db.TxQueryRunner;

public class BookDao {
	//不带JDBCUtils.getDateSource就没有用c3p0连接池
	private QueryRunner qr = new TxQueryRunner(JDBCUtils.getDataSource());
	
	public List<Book> findAll(){
		List<Book> list = null;
		try {
			//1.准备sql模板
			String sql = "SELECT * FROM t_book";
			//2.执行SQL语句,得到结果集，并转换为list
			list = qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		//3.返回list
		return list;
	}
	
	public List<Book> findByCategory(int category){
		List<Book> list = null;
		try {
			//1.准备sql模板
			String sql = "SELECT * FROM t_book WHERE category=?";
			//2.给模板配置参数
			Object param = category;
			//3.执行语句，得到结果集，并转换为list
			list = qr.query(sql, new BeanListHandler<Book>(Book.class), param);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//4.返回list
		return list;
	}
	
}
