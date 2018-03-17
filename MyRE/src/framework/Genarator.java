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

		// 1. ��ȡ��ṹ
		Table table = Config.getTable(tableName);

		// 2. ������ΪСд��DB2�ֶ����͸�ΪJava����
		Column col = table.getColumn();
		DataBaseTypeMapping dbtp = Config.getTypeMapping();
		for(Map.Entry<String, ColAttribute> entry :col.getKeyMap().entrySet()){
			entry.getValue().setJavaType(dbtp.getJavaSimpleValue(entry.getValue().getType()));
		}
		for(Map.Entry<String, ColAttribute> entry :col.getColMap().entrySet()){
			entry.getValue().setJavaType(dbtp.getJavaSimpleValue(entry.getValue().getType()));
		}
		
		// 3. �����ļ�
		Template template = Config.getTemplate(pkg, className, col, tableName); 
		template.print();
	}
	
}
