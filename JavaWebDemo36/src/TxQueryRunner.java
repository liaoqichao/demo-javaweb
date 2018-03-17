import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * 这个类的方法自己来处理连接的问题。无需外界传递。
 * 怎么处理?
 * 通过JDBCUtils.getConnection()得到连接，可能是事务专用连接，可能是普通连接。
 * JDBCUtils.releaseConnction()释放连接。如果是事务连接不处理，如果不是则归还连接给池
 *
 */
public class TxQueryRunner extends QueryRunner{

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * 1.得到连接
		 * 2.执行父类方法,传递连接对象
		 * 3.释放连接(如果不是事务就归还给池,是事务就不归还)
		 * 4.返回值
		 */
		Connection conn = JDBCUtils.getConnection();
		int[] result = super.batch(conn,sql, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtils.getConnection();
		T result = super.insert(conn,sql, rsh, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtils.getConnection();
		T result = super.insert(conn,sql, rsh);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T insertBatch(String sql, ResultSetHandler<T> rsh, Object[][] params) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtils.getConnection();
		T result = super.insertBatch(conn,sql, rsh, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@SuppressWarnings("deprecation")
	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtils.getConnection();
		T result = super.query(conn,sql, param,rsh);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@SuppressWarnings("deprecation")
	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtils.getConnection();
		T result = super.query(conn,sql, params,rsh);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtils.getConnection();
		T result = super.query(conn,sql ,rsh,params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtils.getConnection();
		T result = super.query(conn,sql ,rsh);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtils.getConnection();
		int result = super.update(conn,sql,params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtils.getConnection();
		int result = super.update(conn,sql,param);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JDBCUtils.getConnection();
		int result = super.update(conn,sql);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

		
}
