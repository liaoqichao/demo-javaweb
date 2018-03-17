package oa.issue.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import oa.staff.dao.StaffDao;
import oa.staff.domain.Staff;

/**
 * 签发Dao。这里不是签发的记录（因为签发表没有被签发的文件），而是管理签发权限。 通过在这张表插入和删除记录来管理签发权限。
 */
public class IssueDao {

	private StaffDao staffDao = new StaffDao();

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

	/**
	 * 添加管理权限
	 */
	public void save(Staff staff) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "INSERT INTO issuetable VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, staff.getCardNo());
			pstmt.setString(2, staff.getName());
			pstmt.setString(3, staff.getDepartment());
			pstmt.setString(4, staff.getPosition());
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

	/**
	 * 删除管理权限
	 * 
	 * @param staff
	 * @throws Exception
	 */
	public void delete(Staff staff) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		try {
			// 通过主键查询到要被删除的记录。反正删除都要先通过主键查询。
			// Staff po = findById(staff.getcardNo());
			// System.out.println(po.getcardNo());

			conn = getConnection();
			String sql = "DELETE FROM issuetable WHERE issuerCardNo=" + staff.getCardNo();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}

	public Staff findById(String cardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "SELECT * FROM issuetable WHERE issuerCardNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cardNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Staff staff = new Staff();
				staff.setCardNo(rs.getString(1));
				staff = staffDao.findById(staff);
				System.out.println("here" + staff);
				return staff;
			} else {
				return null;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
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

	/**
	 * 查询所有员工
	 * 
	 * @return 员工列表
	 */
	public List<Staff> findAll() {
		List<Staff> staffList = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			staffList = new ArrayList<Staff>();
			conn = getConnection();
			String sql = "SELECT * FROM issuetable ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Staff staff = new Staff();
				staff.setCardNo(rs.getString(1));
				staff = staffDao.findById(staff);
				staffList.add(staff);
			}
			return staffList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}

	/**
	 * 按department查询
	 * 
	 * @param department
	 * @return 员工列表
	 */
	public List<Staff> findByDepartment(String department) {
		List<Staff> staffList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			staffList = new ArrayList<Staff>();
			conn = getConnection();
			String sql = "SELECT * FROM issuetable WHERE department=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, department);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Staff form = new Staff();
				form.setCardNo(rs.getString(1));
				Staff staff = staffDao.findById(form);
				staffList.add(staff);
			}
			return staffList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
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

	// @Test
	// public void testDelete(){
	// Staff staff = new Staff();
	// staff.setcardNo("123456");
	// delete(staff);
	//// Staff staff = findById("123456");
	//// System.out.println(staff);
	// }

	// @Test
	// public void testSave(){
	// Staff po = new Staff();
	// po.setcardNo("123456");
	// Staff staff = staffDao.findById(po);
	// save(staff);
	// }

	// @Test
	// public void testFindAll(){
	// List<Staff> staffList = findAll();
	// for (Staff staff : staffList) {
	// System.out.println(staff);
	// }
	// }
}
