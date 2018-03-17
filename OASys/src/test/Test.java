package test;

import java.sql.*;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import lqcUtils.db.TxQueryRunner;
import oa.staff.domain.Staff;

public class Test {
	private static QueryRunner qr = new TxQueryRunner();
	public static void main(String[] args) {
		Staff form=new Staff();
		form.setCardNo("122123");
		form.setPassword("sfdad");
		findById(form);
	}
	public static Staff findById(Staff form){
		
		try {System.out.println("2121");
			String sql = "SELECT * FROM stafftable WHERE cardNo=?";
			Staff staff = qr.query(sql, new BeanHandler<Staff>(Staff.class), form.getCardNo());
			return staff;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
