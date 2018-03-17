import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBCUtils v2.0 使用连接池创建得到连接对象
 * @author Administrator
 *
 */
public class JDBCUtils {
	/**
	 * 使用配置文件的默认配置，给出c3p0-config.xml
	 */
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	/**
	 * 事务专用的连接
	 */
	private static ThreadLocal<Connection> threadlocal = new ThreadLocal<Connection>();
	
	/**
	 * @return 返回连接对象。如果没有开启事务，返回的是正常的连接，如果开启了事务，返回的是事务专用连接。
	 */
	public static Connection getConnection(){
		try {
			Connection conn = threadlocal.get();
			//当JDBCUtils.conn不为空说明已经开启了事务
			if(conn != null){
				return conn;
			}
			return cpds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 返回连接池对象
	 * @return
	 */
	public static DataSource getDataSource(){
		return cpds;
	}
	
	/**
	 * 开启事务
	 * 1.创建一个conn
	 * 2.setAutoCommit(false)	
	 * 3.还要保证dao中使用的conn是这个方法创建的conn
	 * @throws SQLException 
	 */
	public static void beginTransaction() throws SQLException{
		//获取当前线程的专用连接
		Connection conn = threadlocal.get();
		
		if(conn != null) throw new SQLException("已经开启了事务，不用再开启");
		
		//1.给成员变量conn赋值
		conn = getConnection();
		try {
			//2.把conn设置为手动提交
			conn.setAutoCommit(false);
			//3.保存当前线程的连接
			threadlocal.set(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 提交事务
	 * 1.获取beginTransaction的conn
	 * 2.conn.commit()
	 * @throws SQLException 
	 */
	public static void commitTransaction() throws SQLException{
		
		Connection conn = threadlocal.get();
		
		if(conn == null) throw new SQLException("还没开始事务，不能提交");
		
		try {
			conn.commit();
			conn.close();
//			conn = null;	//下一次得到事务专用的连接就不会出错，因为事务已经提交了就不能再开始，提交，回滚。
			threadlocal.remove();//从连接本线程中移除
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 提交事务
	 * 1.获取beginTransaction的conn
	 * 2.conn.rollback()
	 * @throws SQLException 
	 */
	public static void rollbackTransaction() throws SQLException{
		//获取当前线程的专用连接
		Connection conn = threadlocal.get();
		if(conn == null) throw new SQLException("还没开始事务，不能回滚");
		try {
			conn.rollback();
			conn.close();
//			conn = null;	//为了下次得到的连接不是已关闭的连接，而是新连接。
			threadlocal.remove();//	把连接从本线程中移除
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 释放连接，把连接归还给池
	 * @param connection
	 */
	public static void releaseConnection(Connection connection){
		/*
		 * 如果是事务就不关闭，因为commitTransaction和rollbackTransaction有close();
		 * 如果不是事务专用,就要关闭
		 */
		Connection conn = threadlocal.get();
		if(conn == null){		//conn=null说明不是事务专用的conn
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		if(conn != connection ){	//传递进来的连接不是事务专用的连接
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//剩下的都是事务专用连接,不用关闭
	}
}