package framework.db;

import java.util.HashMap;
import java.util.Map;

public final class DBMapping {

	public static final String DB2_TABLE = "framework.db.DB2Table";
	public static final String MYSQL_TABLE = "framework.db.MySQL2Table";
	
	public static final String DB2_TYPEMAPPING = "framework.type.DB2TypeMapping";
	public static final String MYSQL_TYPEMAPPING = "";
	
	private static Map<String, String> tableMap;
	private static Map<String, String> typeMap;
	
	public static Map<String,String> getTableMap(){
		if(tableMap == null){
			tableMap = new HashMap<String, String>();
			tableMap.put("db2", DB2_TABLE);
			tableMap.put("mysql", MYSQL_TABLE);
		}
		return tableMap;
	}
	
	public static String getTable(String db){
		if(tableMap == null){
			getTableMap();
		}
		return tableMap.get(db);
	}
	
	public static Map<String, String> getTypeMap(){
		if(typeMap == null){
			typeMap = new HashMap<String, String>();
			typeMap.put("db2", DB2_TYPEMAPPING);
			typeMap.put("mysql", MYSQL_TYPEMAPPING);
		}
		return typeMap;
	}
	
	public static String getTypeMapping(String db){
		if(typeMap == null){
			getTypeMap();
		}
		return typeMap.get(db);
	}
}
