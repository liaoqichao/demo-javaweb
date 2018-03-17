

import java.io.IOException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AServlet
 */
@WebServlet({ "/AServlet", "/demo35_AServlet" })
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//0. CATALINA_HOME\conf\Catalina\localhost�¼�����Ŀ��.xml
		 
		try {
			//1.����JNDI�������Ķ���,�����Context��javax.naming.Context
			Context iniContext = new InitialContext();
			//2.��ѯ�����
//			Context envContext = (Context)iniContext.lookup("java:comp/env");
			//3.���ж��β���,�ҵ���Դ
//			DataSource ds = (DataSource)envContext.lookup("jdbc/dataSource");//�����jdbc/dataSource��xml(��Դ)��name��ֵ
			
			//2,3�����ۺ�Ϊ1��
			DataSource ds = (DataSource)iniContext.lookup("java:comp/env/jdbc/dataSource");
			
			Connection conn = ds.getConnection();
			System.out.println(conn);
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}


}
