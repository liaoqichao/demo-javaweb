package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import domain.Book;
import lqcUtils.db.JDBCUtils;
import lqcUtils.db.TxQueryRunner;

public class BookDao {
	//����JDBCUtils.getDateSource��û����c3p0���ӳ�
	private QueryRunner qr = new TxQueryRunner(JDBCUtils.getDataSource());
	
	public List<Book> findAll(){
		List<Book> list = null;
		try {
			//1.׼��sqlģ��
			String sql = "SELECT * FROM t_book";
			//2.ִ��SQL���,�õ����������ת��Ϊlist
			list = qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		//3.����list
		return list;
	}
	
	public List<Book> findByCategory(int category){
		List<Book> list = null;
		try {
			//1.׼��sqlģ��
			String sql = "SELECT * FROM t_book WHERE category=?";
			//2.��ģ�����ò���
			Object param = category;
			//3.ִ����䣬�õ����������ת��Ϊlist
			list = qr.query(sql, new BeanListHandler<Book>(Book.class), param);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//4.����list
		return list;
	}
	
}
