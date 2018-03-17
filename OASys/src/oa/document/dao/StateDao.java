package oa.document.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oa.document.domain.State;

/**
 * 修改公文的状态
 */
public class StateDao {

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
	 * 刚起草完，增加公文状态
	 */
	public void save(State state) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "INSERT INTO statetable VALUES(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, state.getStateNo());
			pstmt.setString(2, state.getDocumentHeader());
			pstmt.setString(3, state.getCardNo());
			pstmt.setString(4, state.getState());
			pstmt.setObject(5, state.getTime());
			pstmt.setString(6,state.getSendCardNo());

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
	 * 删除公文，同时删除它的状态。
	 * 
	 * @param state
	 */
	public void delete(State state) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "DELETE FROM statetable WHERE stateNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, state.getStateNo());
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
	 * 删除公文，同时删除它的状态。
	 * 
	 * @param state
	 */
	public void deleteByHeader(String header) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "DELETE FROM statetable WHERE documentHeader=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, header);
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
	 * 修改公文状态
	 * 
	 * @param state
	 */
	public void update(State state) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "UPDATE statetable SET state=?," + "time=?, sendCardNo=? WHERE documentHeader=? and CardNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, state.getState());
			pstmt.setObject(2, state.getTime());
			pstmt.setString(3,state.getSendCardNo());
			pstmt.setString(4, state.getDocumentHeader());
			pstmt.setString(5, state.getCardNo());
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

	public List<State> findByDocumentHeader(String documentHeader) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		List<State> stateList = new ArrayList<State>();
		ResultSet rs = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM statetable WHERE documentHeader=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, documentHeader);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				State state = new State();
				state.setStateNo(rs.getString(1));
				state.setDocumentHeader(rs.getString(2));
				state.setCardNo(rs.getString(3));
				state.setState(rs.getString(4));
				state.setTime(rs.getTimestamp(5));
				state.setSendCardNo(rs.getString(6));

				stateList.add(state);
			}
			return stateList;
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

	public State findByHeaderCardNo(String header, String cardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		State state = null;
		ResultSet rs = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM statetable WHERE cardNo=? AND documentHeader=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cardNo);
			pstmt.setString(2, header);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				state = new State();
				state.setStateNo(rs.getString(1));
				state.setDocumentHeader(rs.getString(2));
				state.setCardNo(rs.getString(3));
				state.setState(rs.getString(4));
				state.setTime(rs.getTimestamp(5));
				state.setSendCardNo(rs.getString(6));
			}
			return state;
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
	 * 查询所有跟该用户有关的文档。
	 * 
	 * @param cardNo
	 * @return
	 */
	public List<State> findByStaffCardNo(String cardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<State> stateList = new ArrayList<State>();
		ResultSet rs = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM statetable WHERE cardNo=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cardNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				State state = new State();
				state.setStateNo(rs.getString(1));
				state.setDocumentHeader(rs.getString(2));
				state.setCardNo(rs.getString(3));
				state.setState(rs.getString(4));
				state.setTime(rs.getTimestamp(5));
				state.setSendCardNo(rs.getString(6));

				stateList.add(state);
			}
			return stateList;
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
	 * 根据用户和公文当前状态查询该用户操作的该状态的公文状态
	 * 
	 * @param flag
	 * @param cardNo
	 * @return List<State>不是List<Document>
	 */
	public List<State> findByState(String flag, String cardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<State> stateList = new ArrayList<State>();
		ResultSet rs = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM statetable WHERE cardNo=? AND state=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cardNo);
			pstmt.setString(2, flag);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				State state = new State();
				state.setStateNo(rs.getString(1));
				state.setDocumentHeader(rs.getString(2));
				state.setCardNo(rs.getString(3));
				state.setState(rs.getString(4));
				state.setTime(rs.getTimestamp(5));
				state.setSendCardNo(rs.getString(6));

				stateList.add(state);
			}
			return stateList;
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
	 * @param cardNo
	 * @param documentHeader
	 * @return
	 */
	public List<State> findByDocumentHeader(String cardNo, String documentHeader) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<State> stateList = new ArrayList<State>();
		ResultSet rs = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM statetable WHERE cardNo=? AND documentHeader=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cardNo);
			pstmt.setString(2, documentHeader);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				State state = new State();
				state.setStateNo(rs.getString(1));
				state.setDocumentHeader(rs.getString(2));
				state.setCardNo(rs.getString(3));
				state.setState(rs.getString(4));
				state.setTime(rs.getTimestamp(5));
				state.setSendCardNo(rs.getString(6));

				stateList.add(state);
			}
			return stateList;
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

	public State findByStateNo(String StateNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM statetable WHERE StateNo=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, StateNo);
			rs = pstmt.executeQuery();
			State state = null;
			while (rs.next()) {
				state = new State();
				state.setStateNo(rs.getString(1));
				state.setDocumentHeader(rs.getString(2));
				state.setCardNo(rs.getString(3));
				state.setState(rs.getString(4));
				state.setTime(rs.getTimestamp(5));
				state.setSendCardNo(rs.getString(6));
			}
			return state;
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
	 * 根据发送人查询状态
	 * @param sendCardNo
	 * @return
	 */
	public List<State> findBySendCardNo(String sendCardNo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<State> stateList = new ArrayList<State>();
		ResultSet rs = null;

		try {
			// ------核心代码-------------
			conn = getConnection();
			String sql = "SELECT * FROM statetable WHERE sendCardNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sendCardNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				State state = new State();
				state.setStateNo(rs.getString(1));
				state.setDocumentHeader(rs.getString(2));
				state.setCardNo(rs.getString(3));
				state.setState(rs.getString(4));
				state.setTime(rs.getTimestamp(5));
				state.setSendCardNo(rs.getString(6));
System.out.println(state);
				stateList.add(state);
			}
			return stateList;
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
