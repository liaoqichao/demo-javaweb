package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.domain.User;

/**
 * 针对数据库的实现
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
			//1.得到连接
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			//2.准备SQL模板
			String sql = "SELECT * FROM t_user WHERE username=?";
			pstmt = conn.prepareStatement(sql);
			//3.给问号赋值
			pstmt.setString(1, username);
			//4.执行查询语句
			rs = pstmt.executeQuery();
			//5.把rs转换为User类型
			if(rs == null)
				return null;
			if(rs.next()){
				//转换为User类型
				//ORM ---> 对象关系映射！ 可以借助 hibernate
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getString("status"));
				//user.setAge(rs.getInt("age"));//这里的get类型是SQL类型不是JAVA类型
				
				/*
				 * > 为什么需要sql.Date和java.util.Date类型转换？
				 * 因为值会赋值到JavaBean中，而JavaBean穿梭在各层,而数据访问层之外不能出现sql包。
				 * 所以JavaBean从服务层到数据访问层再到数据库时，数据访问层要把
				 * 		utils.Date --> sql.Date/Time/Timestamp,
				 * 		> 转换：	1.把util.Date转化成long型的毫秒值
				 * 				2.使用毫秒值创建sql.Date/Time/Timestamp
				 * 				java.util.Date date = new java.util.Date();
				 * 				long time = date.getTime();
				 * 				java.sql.Date sql_date = new java.sql.Date(time);
				 * 数据库的数据到数据访问层再通过JavaBean到服务层时，数据访问层需要把
				 * 		sql.Date/Time/Timestamp --> utils.Date
				 * 		> 转换：因为sql.Date/Time/Timestamp是utils.Date的子类。所以转换时：
				 * 		utils.Date date = rs.getDate("date");
				 */
				//user.setDate("date");JavaBean中的不能出现sql包下的东西，不能使用SQL的Date
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
			//1.得到连接
			conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			//2.准备sql模板
			String sql = "INSERT INTO t_user VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			//3.给问号赋值
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getStatus());
			//4.执行语句
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
