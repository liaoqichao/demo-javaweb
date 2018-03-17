import java.sql.SQLException;

import org.junit.Test;

/**
 * ��ʾʹ��JDBCUtils��������.�����Ӧ������ҵ���
 * @author Administrator
 *
 */
public class Demo1 {
	private AccountDao dao = new AccountDao();
	
	public void serviceMethod() throws Exception{
		try {
			JDBCUtils.beginTransaction();
			dao.update("zs", -100);
			
//			if(true){
//				throw new RuntimeException("����,��ع���");
//			}
			
			dao.update("ls", 100);
			JDBCUtils.commitTransaction();
		} catch (Exception e) {
			try {
				JDBCUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw e;
		}
	}
	
	@Test
	public void test() throws Exception{
		serviceMethod();
	}
}
