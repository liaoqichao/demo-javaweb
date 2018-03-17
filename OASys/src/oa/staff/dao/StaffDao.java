package oa.staff.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import oa.staff.domain.Staff;

public class StaffDao {
	/**
	 * ͨ通过卡号查找staff
	 */
	private static String driverClassName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/oasys?useUnicode=true&characterEncoding=UTF-8";
	private static String username = "root";
	private static String password = "123456";

	static {
		// 2.
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 3.得到连接
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Staff findById(Staff form) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Staff staff = null;

		try {
			//------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM stafftable WHERE cardNo=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, form.getCardNo());
			rs = pstmt.executeQuery();
			if(rs.next()){
				staff = new Staff();
				staff.setCardNo(rs.getString(1));
				staff.setName(rs.getString(2));
				staff.setPassword(rs.getString(3));
				staff.setID(rs.getString(4));
				staff.setDepartment(rs.getString(5));
				staff.setPosition(rs.getString(6));
				
			}
			return staff;
			//------------------------
			
		} catch (SQLException e) {

			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查询所有员工
	 * 
	 * @return 员工列表
	 */
	public List<Staff> findAllByDepartment(String department) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Staff> staffList = new ArrayList<Staff>();
		ResultSet rs = null;
		Staff staff = null;

		try {
			//------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM stafftable WHERE department=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, department);
			rs = pstmt.executeQuery();
			while(rs.next()){
				staff = new Staff();
				staff.setCardNo(rs.getString(1));
				staff.setName(rs.getString(2));
				staff.setPassword(rs.getString(3));
				staff.setID(rs.getString(4));
				staff.setDepartment(rs.getString(5));
				staff.setPosition(rs.getString(6));
				staffList.add(staff);
			}
			return staffList;
			//------------------------
			
		} catch (SQLException e) {

			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void save(Staff staff){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "INSERT INTO stafftable VALUES(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, staff.getCardNo());
			pstmt.setString(2, staff.getName());
			pstmt.setString(3, staff.getPassword());
			pstmt.setString(4, staff.getID());
			pstmt.setString(5, staff.getDepartment());
			pstmt.setString(6, staff.getPosition());
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}
	
	public void delete(String cardNo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//------核心代码-------------
			conn = getConnection();
			String sql = "DELETE FROM stafftable WHERE cardNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cardNo);
			pstmt.executeUpdate();
			
			//------------------------
			
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 修改密码
	 * @param cardNo
	 * @param password
	 */
	public void modifyPassword(String cardNo,String password){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			//------核心代码-------------
			conn = getConnection();
			String sql = "UPDATE stafftable SET password=? WHERE cardNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, cardNo);
			pstmt.executeUpdate();
			
			//------------------------
			
		} catch (SQLException e) {

			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	 @Test
	 public void fun1(){
//		 Staff staff = new Staff();
//		 staff.setCardNo(CommonUtils.uuid());
//		 staff.setName("王家老八");
//		 staff.setPassword("888");
//		 staff.setID("1212121");
//		 staff.setDepartment("步布部");
//		 staff.setPosition("部长");
//		 save(staff);
		 
		 //先查后删
//		 Staff form =new Staff();
//		 form.setCardNo("625F24EBCF7B4DF98A8069013C42AD14");
//		 Staff staff = findById(form);
//		 System.out.println(staff);
//		 delete(staff.getCardNo());
		 
		 //修改密码
//		 Staff form =new Staff();
//		 form.setCardNo("123458");
//		 Staff staff = findById(form);
//		 modifyPassword(form.getCardNo(),"mima");
	 }
}
