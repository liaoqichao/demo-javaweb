package oa.document.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oa.document.domain.ModifyDocument;

public class ModifyDocumentDao {

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
	 * 只有增加记录
	 */
	public void save(ModifyDocument modifyDocument) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "INSERT INTO modifydocumentrecord VALUES(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modifyDocument.getModifyDocumentRecordNo());
			pstmt.setString(2, modifyDocument.getDocumentNo());
			pstmt.setString(3, modifyDocument.getDocumentHeader());
			pstmt.setString(4, modifyDocument.getModifyerCardNo());
			pstmt.setObject(5, modifyDocument.getModifyTime());
			pstmt.setString(6, modifyDocument.getRemarks());

			pstmt.executeUpdate();

			// ------------------------

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

	public void update(ModifyDocument modifyDocument) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "UPDATE modifydocumentrecord SET "
					+ "documentHeader=?,modifyTime=?, remarks=? WHERE modifyerCardNo=? AND documentNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modifyDocument.getDocumentHeader());
			pstmt.setObject(2, modifyDocument.getModifyTime());
			pstmt.setString(3, modifyDocument.getRemarks());
			pstmt.setString(4, modifyDocument.getModifyerCardNo());
			pstmt.setString(5, modifyDocument.getDocumentNo());

			pstmt.executeUpdate();

			// ------------------------

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
	 * 
	 * 从ModifyDocumentrecord里，根据用户ModifyCardNo查询他所有处理的文件列表，直接用 List
	 * <ModifyDocument>格式就行，别带文件类型。
	 * 
	 * @param modifyerCardNo
	 * @return
	 */
	public List<ModifyDocument> findByModifyerCardNo(String modifyerCardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<ModifyDocument> modifyDocumentList = new ArrayList<ModifyDocument>();
		ResultSet rs = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM modifydocumentrecord WHERE modifyerCardNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modifyerCardNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ModifyDocument modifyDocument = new ModifyDocument();
				modifyDocument.setModifyDocumentRecordNo(rs.getString(1));
				modifyDocument.setDocumentNo(rs.getString(2));
				modifyDocument.setDocumentHeader(rs.getString(3));
				modifyDocument.setModifyerCardNo(rs.getString(4));
				modifyDocument.setModifyTime(rs.getTimestamp(5));
				modifyDocument.setRemarks(rs.getString(6));
				modifyDocumentList.add(modifyDocument);
			}
			return modifyDocumentList;
			// ------------------------

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

	public ModifyDocument findByDocumentCardNo(String cardNo, String documentNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ModifyDocument modifyDocument = null;
		try {
			// ------鏍稿績浠ｇ爜-------------
			conn = getConnection();
			String sql = "SELECT * FROM modifydocumentrecord WHERE documentNo=? AND modifyerCardNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, documentNo);
			pstmt.setString(2, cardNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				modifyDocument = new ModifyDocument();
				modifyDocument.setModifyDocumentRecordNo(rs.getString(1));
				modifyDocument.setDocumentNo(rs.getString(2));
				modifyDocument.setDocumentHeader(rs.getString(3));
				modifyDocument.setModifyerCardNo(rs.getString(4));
				modifyDocument.setModifyTime(rs.getTimestamp(5));
				modifyDocument.setRemarks(rs.getString(6));
			}
			return modifyDocument;
			// ------------------------

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

	public ModifyDocument findByDocumentHeaderCardNo(String cardNo, String documentHeader) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ModifyDocument modifyDocument = null;
		try {
			// ------鏍稿績浠ｇ爜-------------
			conn = getConnection();
			String sql = "SELECT * FROM modifydocumentrecord WHERE documentHeader=? AND modifyerCardNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, documentHeader);
			pstmt.setString(2, cardNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				modifyDocument = new ModifyDocument();
				modifyDocument.setModifyDocumentRecordNo(rs.getString(1));
				modifyDocument.setDocumentNo(rs.getString(2));
				modifyDocument.setDocumentHeader(rs.getString(3));
				modifyDocument.setModifyerCardNo(rs.getString(4));
				modifyDocument.setModifyTime(rs.getTimestamp(5));
				modifyDocument.setRemarks(rs.getString(6));
			}
			return modifyDocument;
			// ------------------------

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
}
