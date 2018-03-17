package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import domain.Address;
import domain.District;
import lqcUtils.db.JDBCUtils;
import lqcUtils.db.TxQueryRunner;

public class AddressDao {
	private QueryRunner qr = new TxQueryRunner(JDBCUtils.getDataSource());
	
	/*
	 * 1.根据区找Address类的办理登记地点，返回List<Address>
	 * 2.根据区找Address类办理登记地点,返回List<Address>
	 * 
	 * 总结： 就是根据区找List<Address>，得到Address什么都有。
	 */
	
	public List<Address> qureyByDistrict(District district){
		String sql = "SELECT * FROM t_address WHERE did=?";
		Object param = district.getDid();
		
		try {
			return qr.query(sql, new BeanListHandler<Address>(Address.class), param);//返回List<Address>
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Address queryByAid(int aid) {
		String sql = "SELECT * FROM t_address WHERE aid=?";
		Object param = aid;
		try {
			return qr.query(sql, new BeanHandler<Address>(Address.class), param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
