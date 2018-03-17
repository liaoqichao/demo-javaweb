package bookstore.test;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import bookstore.category.domain.Category;
import bookstore.user.domain.User;
import lqcUtils.db.TxQueryRunner;

public class Demo1 {

	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 回忆一下怎么使用DBUtils。发现addUser和addCategory有相同点.
	 * 增删改查都只是传过来的类型不一样。可以用一个BaseDao的泛型类完成增删改查。具体的Dao继承BaseDao
	 * UserDao userDao = new BaseDao<User>();void addUser(User user)可以直接在方法体里面super.add(User)
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
