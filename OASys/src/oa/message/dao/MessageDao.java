package oa.message.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oa.message.domain.Message;

public class MessageDao {

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
	 * 添加信息记录
	 * 
	 * @param message
	 */
	public void save(Message message) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "INSERT INTO messagetable VALUES(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getMessageNo());
			pstmt.setString(2, message.getSendCardNo());
			pstmt.setString(3, message.getSendName());
			pstmt.setString(4, message.getReceiveCardNo());
			pstmt.setString(5, message.getReceiveName());
			pstmt.setInt(6, message.getState());
			pstmt.setString(7, message.getMessage());
			pstmt.setObject(8, message.getTime());

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
	 * 根据主键查询
	 * 
	 * @param messageNo
	 */
	public Message findByNo(String messageNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Message message = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM messagetable WHERE messageNo=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, messageNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				message = new Message();
				message.setMessageNo(rs.getString(1));
				message.setSendCardNo(rs.getString(2));
				message.setSendName(rs.getString(3));
				message.setReceiveCardNo(rs.getString(4));
				message.setReceiveName(rs.getString(5));
				message.setState(rs.getInt(6));
				message.setMessage(rs.getString(7));
				message.setTime(rs.getTimestamp(8));
			}
			return message;
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
	 * 修改信息
	 * 
	 * @param message
	 */
	public void update(Message message) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "UPDATE messagetable SET " + "sendCardNo=?, sendName=?, receiveCardNo=?, receiveName=?,"
					+ "state=?,message=?, time=? WHERE messageNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getSendCardNo());
			pstmt.setString(2, message.getSendName());
			pstmt.setString(3, message.getReceiveCardNo());
			pstmt.setString(4, message.getReceiveName());
			pstmt.setInt(5, message.getState());
			pstmt.setString(6, message.getMessage());
			pstmt.setObject(7, message.getTime());
			pstmt.setString(8, message.getMessageNo());

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
	 * 根据主键删除
	 * 
	 * @param messageNo
	 */
	public void delete(String messageNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "DELETE FROM messagetable WHERE messageNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, messageNo);
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
	 * 根据发送人查询信息列表
	 * 
	 * @param sendCardNo
	 * @return
	 */
	public List<Message> findBySendCardNo(String sendCardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Message> messageList = new ArrayList<Message>();
		ResultSet rs = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM messagetable WHERE sendCardNo=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sendCardNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Message message = new Message();
				message.setMessageNo(rs.getString(1));
				message.setSendCardNo(rs.getString(2));
				message.setSendName(rs.getString(3));
				message.setReceiveCardNo(rs.getString(4));
				message.setReceiveName(rs.getString(5));
				message.setState(rs.getInt(6));
				message.setMessage(rs.getString(7));
				message.setTime(rs.getTimestamp(8));

				messageList.add(message);
			}
			return messageList;
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
	 * 根据接收人查询信息列表
	 * 
	 * @param sendCardNo
	 * @return
	 */
	public List<Message> findByReceiveCardNo(String receiveCardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Message> messageList = new ArrayList<Message>();
		ResultSet rs = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM messagetable WHERE receiveCardNo=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, receiveCardNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Message message = new Message();
				message.setMessageNo(rs.getString(1));
				message.setSendCardNo(rs.getString(2));
				message.setSendName(rs.getString(3));
				message.setReceiveCardNo(rs.getString(4));
				message.setReceiveName(rs.getString(5));
				message.setState(rs.getInt(6));
				message.setMessage(rs.getString(7));
				message.setTime(rs.getTimestamp(8));

				messageList.add(message);
			}
			return messageList;
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
	 * 根据接收人查询信息列表
	 * 
	 * @param sendCardNo
	 * @return
	 */
	public List<Message> findByReceiveCardNoState(String receiveCardNo,int state) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Message> messageList = new ArrayList<Message>();
		ResultSet rs = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM messagetable WHERE receiveCardNo=? AND state=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, receiveCardNo);
			pstmt.setInt(2, state);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Message message = new Message();
				message.setMessageNo(rs.getString(1));
				message.setSendCardNo(rs.getString(2));
				message.setSendName(rs.getString(3));
				message.setReceiveCardNo(rs.getString(4));
				message.setReceiveName(rs.getString(5));
				message.setState(rs.getInt(6));
				message.setMessage(rs.getString(7));
				message.setTime(rs.getTimestamp(8));

				messageList.add(message);
			}
			return messageList;
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
	 * 根据接收人，发送人查询信息列表
	 * @param sendoCardNo
	 * @param sendCardNo
	 * @return
	 */
	public List<Message> findByReceiveSendCardNo(String name,String receiveCardNo) {System.out.println(name+" "+receiveCardNo);
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Message> messageList = new ArrayList<Message>();
		ResultSet rs = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM messagetable WHERE sendName=? AND receiveCardNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2,receiveCardNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Message message = new Message();
				message.setMessageNo(rs.getString(1));
				message.setSendCardNo(rs.getString(2));
				message.setSendName(rs.getString(3));
				message.setReceiveCardNo(rs.getString(4));
				message.setReceiveName(rs.getString(5));
				message.setState(rs.getInt(6));
				message.setMessage(rs.getString(7));
				message.setTime(rs.getTimestamp(8));

				messageList.add(message);
			}
			return messageList;
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
