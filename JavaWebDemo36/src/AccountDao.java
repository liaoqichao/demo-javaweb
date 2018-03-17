

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class AccountDao {
	public void update(String name, double money) throws SQLException{
		QueryRunner qr = new TxQueryRunner();
		String sql = "UPDATE account SET balance=balance+? WHERE name=?";
		Object[] params = {money, name};
		//保证多次调用时同一个连接,通常调用这个方法的时候前面代码就有了JDBCUtils.beginTransaction。
//		JDBCUtils包成员conn不为空了，所以这个返回的是不为空的成员变量，而不是新的conn
		
//		Connection conn = JDBCUtils.getConnection();
//		qr.update(conn,sql, params);
//		JDBCUtils.releaseConnection(conn);//如果是事务专用连接就不关闭,否则关闭
		
		//new TxQueryRunner后上面3行代码变成1行。在调用的方法里面获取连接和释放连接。
		qr.update(sql,params);
	}
}
