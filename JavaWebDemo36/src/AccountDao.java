

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class AccountDao {
	public void update(String name, double money) throws SQLException{
		QueryRunner qr = new TxQueryRunner();
		String sql = "UPDATE account SET balance=balance+? WHERE name=?";
		Object[] params = {money, name};
		//��֤��ε���ʱͬһ������,ͨ���������������ʱ��ǰ����������JDBCUtils.beginTransaction��
//		JDBCUtils����Աconn��Ϊ���ˣ�����������ص��ǲ�Ϊ�յĳ�Ա�������������µ�conn
		
//		Connection conn = JDBCUtils.getConnection();
//		qr.update(conn,sql, params);
//		JDBCUtils.releaseConnection(conn);//���������ר�����ӾͲ��ر�,����ر�
		
		//new TxQueryRunner������3�д�����1�С��ڵ��õķ��������ȡ���Ӻ��ͷ����ӡ�
		qr.update(sql,params);
	}
}
