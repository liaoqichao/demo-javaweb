package bookstore.test;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import bookstore.category.domain.Category;
import bookstore.user.domain.User;
import lqcUtils.db.TxQueryRunner;

public class Demo1 {

	private QueryRunner qr = new TxQueryRunner();
	/**
	 * ����һ����ôʹ��DBUtils������addUser��addCategory����ͬ��.
	 * ��ɾ�Ĳ鶼ֻ�Ǵ����������Ͳ�һ����������һ��BaseDao�ķ����������ɾ�Ĳ顣�����Dao�̳�BaseDao
	 * UserDao userDao = new BaseDao<User>();void addUser(User user)����ֱ���ڷ���������super.add(User)
	 */
	public void addUser(User user){
		try {
			String sql = "";
			Object[] params = {};
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addCategory(Category category){
		try {
			String sql = "";
			Object[] params = {};
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
