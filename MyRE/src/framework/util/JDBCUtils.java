package framework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import framework.config.Config;

public class JDBCUtils {

	private static final String DRIVER_CLASS_NAME = "driver_class_name";
	private static final String URL = "url";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	private static Properties prop = Config.getProperties();
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName(prop.getProperty(DRIVER_CLASS_NAME));
		Connection conn = DriverManager.getConnection(prop.getProperty(URL), prop.getProperty(USERNAME), prop.getProperty(PASSWORD));
		return conn;
	}
}
