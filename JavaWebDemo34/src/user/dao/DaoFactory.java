package user.dao;

import java.io.InputStream;
import java.util.Properties;

/**
 * ����һ�������ļ����ļ�����UserDao�ӿڵ�ʵ��������,ͨ��������ɴ�������
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
