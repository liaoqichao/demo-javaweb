package bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import bookstore.user.domain.User;
import lqcUtils.db.TxQueryRunner;

/**
 * User持久层
 */
public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 通过用户名查询用户，为了防止用户名相同
	 * @param username
	 * @return
	 */
	public User findByUsername(String username){
		try {
			String sql = "SELECT * FROM tb_user WHERE username=?";
			//username打错，前面抛出的异常的Exception异常没有显示SQL语句错误，换成SQLException才显示，所以异常类越精确越好
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 通过email查询用户,为了防止email相同
	 * @param username
	 * @return
	 */
	public User findByEmail(String email){
		try {
			String sql = "SELECT * FROM tb_user WHERE email=?";
			return qr.query(sql, new BeanHandler<User>(User.class), email);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void add(User user){
		try {
			String sql = "INSERT INTO tb_user VALUES(?,?,?,?,?,?)";
			Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),user.getEmail(),
			                   user.getCode(),user.isState()};
			qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 通过激活码查询用户
	 * @param code 激活码
	 */
	public User findByCode(String code){
		try {
			String sql = "SELECT * FROM tb_user WHERE code=?";
			return qr.query(sql, new BeanHandler<User>(User.class),code);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 修改用户的激活状态
	 * @param uid
	 * @param state
	 */
	public void updateState(String uid,boolean state){
		try {
			String sql = "UPDATE tb_user SET state=? WHERE uid=?";
			qr.update(sql, state,uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
