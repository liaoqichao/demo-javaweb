import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * �����ķ����Լ����������ӵ����⡣������紫�ݡ�
 * ��ô����?
 * ͨ��JDBCUtils.getConnection()�õ����ӣ�����������ר�����ӣ���������ͨ���ӡ�
 * JDBCUtils.releaseConnction()�ͷ����ӡ�������������Ӳ��������������黹���Ӹ���
 *
 */
public class TxQueryRunner extends QueryRunner{

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * 1.�õ�����
		 * 2.ִ�и��෽��,�������Ӷ���
		 * 3.�ͷ�����(�����������͹黹����,������Ͳ��黹)
		 * 4.����ֵ
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
