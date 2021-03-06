package oa.draft.dao;

import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oa.document.domain.Document;
import utils.IOUtils;

/**
 * 公文起草：数据访问层
 */
public class DraftDao {

//	private static DataSource cpds = new ComboPooledDataSource();
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
	// .得到连接
	public static Connection getConnection() {
		try {
//			return cpds.getConnection();C
			return DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 向filetable添加记录
	 */
	public void save(Document document) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			/*
			 * 获取File，并转换为Blob
			 */
			File file = document.getDocument();
			new IOUtils();
			Blob blob = IOUtils.fileToBlob(file);
			conn = getConnection();
			String sql = "INSERT INTO filetable VALUES(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, document.getDocumentNo());
			pstmt.setString(2, document.getDocumentHeader());
			pstmt.setObject(3, blob, java.sql.Types.BLOB);
			pstmt.setString(4, document.getDraftsmanCardNo());
			pstmt.setString(5, document.getIssuerCardNo());
			pstmt.setString(6, document.getSuffix());
			pstmt.setString(7, document.getDocumentName());
			pstmt.executeUpdate();
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
	 * 通过主键查询
	 * 
	 * @param fileNo
	 * @return
	 */
	public Document findById(String documentNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Document document = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM filetable WHERE fileNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, documentNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				document = new Document();
				document.setDocumentNo(rs.getString(1));
				document.setDocumentHeader(rs.getString(2));

				Blob blob = rs.getBlob(3);
				String destPath ="C:/OASys/"+rs.getString(2);
				// String destPath =
				// "E:/OASys/"+rs.getString(1)+"_"+rs.getString(2)+rs.getString(6);
//				new IOUtils();
				IOUtils.blobToLocalDisk(blob, destPath);
				File file = new File(destPath);
				document.setDocument(file);

				document.setDraftsmanCardNo(rs.getString(4));
				document.setIssuerCardNo(rs.getString(5));

			}
			return document;
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
	 * 删除公文
	 * 
	 * @param document
	 */
	public void delete(Document document) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "DELETE FROM filetable WHERE fileNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, document.getDocumentNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 修改公文
	 * 
	 * @param document
	 */
	public void update(Document document) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "UPDATE filetable SET fileHeader=?,"
					+ "file=?,draftmanCardNo=?,issuerCardNo=?,suffix=?, fileName=? WHERE fileNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, document.getDocumentHeader());
			File file = document.getDocument();
			Blob blob = IOUtils.fileToBlob(file);
			pstmt.setBlob(2, blob);
			pstmt.setString(3, document.getDraftsmanCardNo());
			pstmt.setString(4, document.getIssuerCardNo());
			pstmt.setString(5, document.getSuffix());
			pstmt.setString(6, document.getDocumentName());
			pstmt.setString(7, document.getDocumentNo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 根据主题模糊查询
	 * 
	 * @param documentNo
	 * @return
	 */
	public List<Document> findByHeaderCardNo(String documentHeader, String cardNo) {
		List<Document> documentList = new ArrayList<Document>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Document document = null;
		try {
			conn = getConnection();
			String sql = "SELECT * FROM filetable WHERE draftmanCardNo=? AND fileHeader LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cardNo);
			pstmt.setString(2, "%" + documentHeader + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {// 没有进入循环
				document = new Document();
				document.setDocumentNo(rs.getString(1));
				document.setDocumentHeader(rs.getString(2));

				Blob blob = rs.getBlob(3);

				// 有则不创建，没有则创建。/A/B这里先创建B目录
				String destPath = "C:/";
				File dir = new File(destPath, "OASys");
				dir.mkdir();
				destPath = destPath + "OASys/";

//				destPath = destPath + rs.getString(1) + "_" + rs.getString(2) + rs.getString(6);
				destPath = destPath + rs.getString(1)+ "_" + rs.getString(7);

				System.out.println(destPath);

				new IOUtils();
				IOUtils.blobToLocalDisk(blob, destPath);
				File file = new File(destPath);
				document.setDocument(file);

				document.setDraftsmanCardNo(rs.getString(4));
				document.setIssuerCardNo(rs.getString(5));

				documentList.add(document);
			}
			return documentList;
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
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public String findNoByHeader(String documentHeader, String cardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String documentNo = "";
		try {
			conn = getConnection();
			String sql = "SELECT fileNo FROM filetable WHERE fileHeader= ? AND issuerCardNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, documentHeader);
			pstmt.setString(2, cardNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {// 没有进入循环
				documentNo = rs.getString("fileNo");
			}
			return documentNo;
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
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 根据主题模糊查询
	 * 
	 * @param documentNo
	 * @return
	 */
	public String findHeaderById(String documentNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Document document = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT fileHeader FROM filetable WHERE fileNo=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, documentNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				document = new Document();
				document.setDocumentHeader(rs.getString("fileHeader"));
			}
			return document.getDocumentHeader();
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
	 * 没有文件
	 * @return
	 */
	public Document findByDocumentNo(String documentNo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Document document = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM filetable WHERE fileNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, documentNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				document = new Document();
				document.setDocumentNo(rs.getString(1));
				document.setDocumentHeader(rs.getString(2));
				
				document.setDraftsmanCardNo(rs.getString(4));
				document.setIssuerCardNo(rs.getString(5));

			}
			return document;
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
	public Document findByDocumentHeader(String documentHeader){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Document document = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM filetable WHERE fileHeader=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, documentHeader);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				document = new Document();
				document.setDocumentNo(rs.getString(1));
				document.setDocumentHeader(rs.getString(2));
				document.setDocument(null);
				
				document.setDraftsmanCardNo(rs.getString(4));
				document.setIssuerCardNo(rs.getString(5));

			}
			return document;
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
