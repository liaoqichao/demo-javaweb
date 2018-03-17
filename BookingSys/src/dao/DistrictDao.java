package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import domain.District;
import lqcUtils.db.JDBCUtils;
import lqcUtils.db.TxQueryRunner;

public class DistrictDao {
	private QueryRunner qr = new TxQueryRunner(JDBCUtils.getDataSource());
	
	/**
	 * 根据区名找区
	 * @param dname
	 * @return
	 */
	public District queryByDid(int did){
		String sql = "SELECT * FROM t_district WHERE did=?";
		Object param = did;
		try {
			return qr.query(sql, new BeanHandler<District>(District.class), param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查询所有区的名字，返回List<District>
	 * @return
	 */
	public List<District> queryAll(){
		String sql = "SELECT * FROM t_district";
		List<District> list;
		try {
			list= qr.query(sql, new BeanListHandler<District>(District.class));
			return list ;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
