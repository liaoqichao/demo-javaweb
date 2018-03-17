package update.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 这个类已经放在工具包，可以不用。通过lqcUtils.LoadProperties这个类来调用
 * 读取配置文件的类
 */
public class LoadProperties {

	private Properties properties;
	public LoadProperties(String propertiesFileName){
		// 不能使用.getResourceAsStream，不然手动修改properties还是没有读到更改后的。
		File file = null;
		InputStream in = null; 
//		InputStream in = this.getClass().getClassLoader().getResourceAsStream(propertiesFileName);
		Properties prop = new Properties();
		try {
			file = new File("src/"+propertiesFileName);
			in = new FileInputStream(file);
			prop.load(in);
		} catch (IOException e) {
			System.out.println("没有读取到Properties文件，请确认路径");
			e.printStackTrace();
		}
		this.properties = prop;
	}
	
	public Properties getProperties(){
		return this.properties;
	}
}
