package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import lqcUtils.db.JDBCUtils;
import lqcUtils.db.TxQueryRunner;

import domain.Client;

public class BookingItemDao {
	private QueryRunner qr = new TxQueryRunner(JDBCUtils.getDataSource());
	
	@Test
	public void fun1() throws SQLException{
		String sql = "SELECT * FROM t_client where cid=?";
		Object param = 1;
		Client c1 = qr.query(sql, new BeanHandler<Client>(Client.class), param);
		System.out.println(c1);
	}
}
