package template.printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import framework.db.ColAttribute;
import framework.db.Column;
import framework.encoder.Privilege;
import framework.printer.Method;
import framework.printer.MethodPrinter;
import framework.util.WritelnWriter;

public class DaoMethodPrinter extends MethodPrinter {

	public DaoMethodPrinter(WritelnWriter ww) {
		super(ww);
	}
	

	public List<Method> getMethodList(String pkg, String className, String tableName, Column col) {
		List<Method> methodList = new ArrayList<Method>();
		// createObject·½·¨
		Method co = new Method(Privilege.PUBLIC,className,"createObject");
		Map<String,String> varAndTypeMap = new HashMap<String,String>();
		varAndTypeMap.put("rs", "ResultSet");
		String[] exceptions = {"Exception"};
		co.setExceptions(exceptions);
		co.setVarAndTypeMap(varAndTypeMap);
		co.setReturnVariable(className.toLowerCase());
		co.setBody(getBodyString(className,col));
		methodList.add(co);
		
		// getIdColumn
		Method getIdMethod = new Method(Privilege.PUBLIC,"String","getIdColumn");
		getIdMethod.setReturnVariable("\""+getId(col)+"\"");
		methodList.add(getIdMethod);
		
		// getTableName
		Method getTableMethod = new Method(Privilege.PUBLIC,"String","getTableName");
		getTableMethod.setReturnVariable("\""+tableName+"\"");
		methodList.add(getTableMethod);
		return methodList;
	}
	

	private String getBodyString(String className, Column col) {
		StringBuilder sb = new StringBuilder();
		String variableName = className.toLowerCase();
		sb.append(className+" "+variableName +" = new "+className+"();");
		sb.append(System.getProperty("line.separator"));
		sb.append(createObjectCol(col.getKeyMap(), variableName));
		sb.append(createObjectCol(col.getColMap(), variableName));
		return sb.toString();
	}

	private String createObjectCol(Map<String, ColAttribute> colMap, String variableName) {
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String,ColAttribute> entry : colMap.entrySet()){
			String member = entry.getKey().substring(0,1).toUpperCase() + entry.getKey().substring(1);
			sb.append("\t\t"+variableName+".set"+member +"(rs.get"+this.dbtp.getJdbcValue(entry.getValue().getType())+"(\""+entry.getKey()+"\"));");
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	private String getId(Column col) {
		Set<String> keySet = col.getKeyMap().keySet();
		String key = "";
		if(keySet.size() == 1){
			for (String string : keySet) {
				key = string;
			}
		}
		return key;
	}
}
