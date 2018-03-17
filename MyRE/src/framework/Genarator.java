package framework;

import java.util.Map;

import framework.config.Config;
import framework.db.ColAttribute;
import framework.db.Column;
import framework.db.Table;
import framework.template.Template;
import framework.type.DataBaseTypeMapping;

public class Genarator {

	public void genarator(String pkg, String className, String tableName) throws Exception{

		// 1. 获取表结构
		Table table = Config.getTable(tableName);

		// 2. 列名改为小写，DB2字段类型改为Java类型
		Column col = table.getColumn();
		DataBaseTypeMapping dbtp = Config.getTypeMapping();
		for(Map.Entry<String, ColAttribute> entry :col.getKeyMap().entrySet()){
			entry.getValue().setJavaType(dbtp.getJavaSimpleValue(entry.getValue().getType()));
		}
		for(Map.Entry<String, ColAttribute> entry :col.getColMap().entrySet()){
			entry.getValue().setJavaType(dbtp.getJavaSimpleValue(entry.getValue().getType()));
		}
		
		// 3. 生成文件
		Template template = Config.getTemplate(pkg, className, col, tableName); 
		template.print();
	}
	
}
