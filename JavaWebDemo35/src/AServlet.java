

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
		
		//0. CATALINA_HOME\conf\Catalina\localhost下加上项目名.xml
		 
		try {
			//1.创建JNDI的上下文对象,这里的Context是javax.naming.Context
			Context iniContext = new InitialContext();
			//2.查询出入口
//			Context envContext = (Context)iniContext.lookup("java:comp/env");
			//3.进行二次查找,找到资源
//			DataSource ds = (DataSource)envContext.lookup("jdbc/dataSource");//这里的jdbc/dataSource是xml(资源)中name的值
			
			//2,3可以综合为1步
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
