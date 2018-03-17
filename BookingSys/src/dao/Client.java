package dao;

import org.apache.commons.dbutils.QueryRunner;

import lqcUtils.db.JDBCUtils;
import lqcUtils.db.TxQueryRunner;

public class Client {
	private QueryRunner qr = new TxQueryRunner(JDBCUtils.getDataSource());
}
