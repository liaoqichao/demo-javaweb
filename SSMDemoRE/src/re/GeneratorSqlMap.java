package re;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * SSMDemo���򹤳�
 * @author Liaoqichao
 * @date 2016��4��18��
 */
public class GeneratorSqlMap {

	public static void main(String[] args) {
		try {
			GeneratorSqlMap generatorSqlMap = new GeneratorSqlMap();
			generatorSqlMap.generator(); // ִ�����ɴ���
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ����̶�
	public void generator() throws Exception{
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File("config/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
