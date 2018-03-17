package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.domain.User;

/**
 * ������ݿ��ʵ��
 * @author Administrator
 *
 */
public class JdbcUserDaoImpl implements UserDao {

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/demo1";
		String dbUsername = "root";
		String dbPassword = "123";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driverClassName);
			//1.�õ�����
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			//2.׼��SQLģ��
			String sql = "SELECT * FROM t_user WHERE username=?";
			pstmt = conn.prepareStatement(sql);
			//3.���ʺŸ�ֵ
			pstmt.setString(1, username);
			//4.ִ�в�ѯ���
			rs = pstmt.executeQuery();
			//5.��rsת��ΪUser����
			if(rs == null)
				return null;
			if(rs.next()){
				//ת��ΪUser����
				//ORM ---> �����ϵӳ�䣡 ���Խ��� hibernate
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getString("status"));
				//user.setAge(rs.getInt("age"));//�����get������SQL���Ͳ���JAVA����
				
				/*
				 * > Ϊʲô��Ҫsql.Date��java.util.Date����ת����
				 * ��Ϊֵ�ḳֵ��JavaBean�У���JavaBean�����ڸ���,�����ݷ��ʲ�֮�ⲻ�ܳ���sql����
				 * ����JavaBean�ӷ���㵽���ݷ��ʲ��ٵ����ݿ�ʱ�����ݷ��ʲ�Ҫ��
				 * 		utils.Date --> sql.Date/Time/Timestamp,
				 * 		> ת����	1.��util.Dateת����long�͵ĺ���ֵ
				 * 				2.ʹ�ú���ֵ����sql.Date/Time/Timestamp
				 * 				java.util.Date date = new java.util.Date();
				 * 				long time = date.getTime();
				 * 				java.sql.Date sql_date = new java.sql.Date(time);
				 * ���ݿ�����ݵ����ݷ��ʲ���ͨ��JavaBean�������ʱ�����ݷ��ʲ���Ҫ��
				 * 		sql.Date/Time/Timestamp --> utils.Date
				 * 		> ת������Ϊsql.Date/Time/Timestamp��utils.Date�����ࡣ����ת��ʱ��
				 * 		utils.Date date = rs.getDate("date");
				 */
				//user.setDate("date");JavaBean�еĲ��ܳ���sql���µĶ���������ʹ��SQL��Date
				return user;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/demo1";
		String dbUsername = "root";
		String dbPassword = "123";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driverClassName);
			//1.�õ�����
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			//2.׼��sqlģ��
			String sql = "INSERT INTO t_user VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			//3.���ʺŸ�ֵ
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getStatus());
			//4.ִ�����
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
