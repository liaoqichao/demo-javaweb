package framework.config;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;

import framework.db.Column;
import framework.db.DBMapping;
import framework.db.Table;
import framework.template.Template;
import framework.type.DataBaseTypeMapping;
import framework.util.JDBCUtils;

public class Config {

	private static Properties prop;
	
	static{
		prop = new Properties();
		try {
			prop.load(JDBCUtils.class.getClassLoader().getResourceAsStream("generator.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getProperties(){
		return prop;
	}
	
	public static DataBaseTypeMapping getTypeMapping() throws Exception{
		Class<?> clazz = Class.forName(DBMapping.getTypeMapping(prop.getProperty("database")));
		DataBaseTypeMapping dbtp = (DataBaseTypeMapping) clazz.newInstance();
		return dbtp;
	}
	
	public static Table getTable(String tableName) throws Exception{
		Class<?> tableClazz = Class.forName(DBMapping.getTable(prop.getProperty("database")));
		Constructor<?> tableConstructor = tableClazz.getConstructor(String.class);
		Table table = (Table) tableConstructor.newInstance(tableName);
		return table;
	}
	
	public static Template getTemplate(String pkg, String className, Column col, String tableName) throws Exception{
		Class<?> templateClazz = Class.forName(prop.getProperty("template"));
		Constructor<?> templateConstructor = templateClazz.getConstructor(String.class,String.class,Column.class,String.class);
		Template template = (Template) templateConstructor.newInstance(pkg, className, col, tableName);
		return template;
	}
}
