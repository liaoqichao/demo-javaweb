package bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import bookstore.user.domain.User;
import lqcUtils.db.TxQueryRunner;

/**
 * User�־ò�
 */
public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * ͨ���û�����ѯ�û���Ϊ�˷�ֹ�û�����ͬ
	 * @param username
	 * @return
	 */
	public User findByUsername(String username){
		try {
			String sql = "SELECT * FROM tb_user WHERE username=?";
			//username���ǰ���׳����쳣��Exception�쳣û����ʾSQL�����󣬻���SQLException����ʾ�������쳣��Խ��ȷԽ��
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * ͨ��email��ѯ�û�,Ϊ�˷�ֹemail��ͬ
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
	 * ����û�
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
	 * ͨ���������ѯ�û�
	 * @param code ������
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
	 * �޸��û��ļ���״̬
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
