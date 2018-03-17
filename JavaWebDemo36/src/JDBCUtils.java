import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBCUtils v2.0 ʹ�����ӳش����õ����Ӷ���
 * @author Administrator
 *
 */
public class JDBCUtils {
	/**
	 * ʹ�������ļ���Ĭ�����ã�����c3p0-config.xml
	 */
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	/**
	 * ����ר�õ�����
	 */
	private static ThreadLocal<Connection> threadlocal = new ThreadLocal<Connection>();
	
	/**
	 * @return �������Ӷ������û�п������񣬷��ص������������ӣ�������������񣬷��ص�������ר�����ӡ�
	 */
	public static Connection getConnection(){
		try {
			Connection conn = threadlocal.get();
			//��JDBCUtils.conn��Ϊ��˵���Ѿ�����������
			if(conn != null){
				return conn;
			}
			return cpds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �������ӳض���
	 * @return
	 */
	public static DataSource getDataSource(){
		return cpds;
	}
	
	/**
	 * ��������
	 * 1.����һ��conn
	 * 2.setAutoCommit(false)	
	 * 3.��Ҫ��֤dao��ʹ�õ�conn���������������conn
	 * @throws SQLException 
	 */
	public static void beginTransaction() throws SQLException{
		//��ȡ��ǰ�̵߳�ר������
		Connection conn = threadlocal.get();
		
		if(conn != null) throw new SQLException("�Ѿ����������񣬲����ٿ���");
		
		//1.����Ա����conn��ֵ
		conn = getConnection();
		try {
			//2.��conn����Ϊ�ֶ��ύ
			conn.setAutoCommit(false);
			//3.���浱ǰ�̵߳�����
			threadlocal.set(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �ύ����
	 * 1.��ȡbeginTransaction��conn
	 * 2.conn.commit()
	 * @throws SQLException 
	 */
	public static void commitTransaction() throws SQLException{
		
		Connection conn = threadlocal.get();
		
		if(conn == null) throw new SQLException("��û��ʼ���񣬲����ύ");
		
		try {
			conn.commit();
			conn.close();
//			conn = null;	//��һ�εõ�����ר�õ����ӾͲ��������Ϊ�����Ѿ��ύ�˾Ͳ����ٿ�ʼ���ύ���ع���
			threadlocal.remove();//�����ӱ��߳����Ƴ�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �ύ����
	 * 1.��ȡbeginTransaction��conn
	 * 2.conn.rollback()
	 * @throws SQLException 
	 */
	public static void rollbackTransaction() throws SQLException{
		//��ȡ��ǰ�̵߳�ר������
		Connection conn = threadlocal.get();
		if(conn == null) throw new SQLException("��û��ʼ���񣬲��ܻع�");
		try {
			conn.rollback();
			conn.close();
//			conn = null;	//Ϊ���´εõ������Ӳ����ѹرյ����ӣ����������ӡ�
			threadlocal.remove();//	�����Ӵӱ��߳����Ƴ�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �ͷ����ӣ������ӹ黹����
	 * @param connection
	 */
	public static void releaseConnection(Connection connection){
		/*
		 * ���������Ͳ��رգ���ΪcommitTransaction��rollbackTransaction��close();
		 * �����������ר��,��Ҫ�ر�
		 */
		Connection conn = threadlocal.get();
		if(conn == null){		//conn=null˵����������ר�õ�conn
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		if(conn != connection ){	//���ݽ��������Ӳ�������ר�õ�����
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//ʣ�µĶ�������ר������,���ùر�
	}
}