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
	 * 1.��������Address��İ���Ǽǵص㣬����List<Address>
	 * 2.��������Address�����Ǽǵص�,����List<Address>
	 * 
	 * �ܽ᣺ ���Ǹ�������List<Address>���õ�Addressʲô���С�
	 */
	
	public List<Address> qureyByDistrict(District district){
		String sql = "SELECT * FROM t_address WHERE did=?";
		Object param = district.getDid();
		
		try {
			return qr.query(sql, new BeanListHandler<Address>(Address.class), param);//����List<Address>
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
