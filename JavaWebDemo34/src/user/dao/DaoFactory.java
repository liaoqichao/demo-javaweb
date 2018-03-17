package user.dao;

import java.io.InputStream;
import java.util.Properties;

/**
 * 给出一个配置文件，文件给出UserDao接口的实现类名称,通过反射完成创建对象。
 */
public class DaoFactory {
	private static Properties prop = null;
	static{
		try {
			InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
			prop = new Properties();
			prop.load(in);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static UserDao getUserDao(){
		try{
			String daoClassName = prop.getProperty("user.dao.UserDao");
			@SuppressWarnings("rawtypes")
			Class clazz = Class.forName(daoClassName);
			return (UserDao) clazz.newInstance();
		}catch(Exception e){
			throw new RuntimeException(e);
		}

	}
}
