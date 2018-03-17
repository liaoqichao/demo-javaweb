package framework.type;

import java.util.HashMap;
import java.util.Map;

public final class DB2TypeMapping extends DataBaseTypeMapping{

	private static Map<String,String> db2JavaMap;
	private static Map<String,String> db2JdbcMap;
	
	public Map<String, String> getJavaMap(){
		if(db2JavaMap == null){
			db2JavaMap = new HashMap<String,String>();
			db2JavaMap.put(DB2Type.BIGINT, JavaType.LONG);
			db2JavaMap.put(DB2Type.BLOB, JavaType.BYTE_ARR);
			db2JavaMap.put(DB2Type.CHAR, JavaType.STRING);
			db2JavaMap.put(DB2Type.CLOB, JavaType.STRING);
			db2JavaMap.put(DB2Type.DATE, JavaType.SQL_DATE);
			db2JavaMap.put(DB2Type.DECIMAL, JavaType.BIG_DECIMAL);
			db2JavaMap.put(DB2Type.FLOAT, JavaType.DOUBLE);
			db2JavaMap.put(DB2Type.INTEGER, JavaType.Integer);
			db2JavaMap.put(DB2Type.NUMERIC, JavaType.BIG_DECIMAL);
			db2JavaMap.put(DB2Type.REAL, JavaType.FLOAT);
			db2JavaMap.put(DB2Type.TIME, JavaType.SQL_TIME);
			db2JavaMap.put(DB2Type.TIMESTAMP, JavaType.TIME_STAMP);
			db2JavaMap.put(DB2Type.VARCHAR, JavaType.STRING);
		}
		return db2JavaMap;
	}
	
	public String getJavaValue(String type){
		if(db2JavaMap == null){
			db2JavaMap = getJavaMap();
		}
		return db2JavaMap.get(type);
	}
	
	public Map<String, String> getJdbcMap(){
		if(db2JdbcMap == null){
			db2JdbcMap = new HashMap<String,String>();
			db2JdbcMap.put(DB2Type.BIGINT, JDBCType.LONG);
			db2JdbcMap.put(DB2Type.BLOB, JDBCType.BLOB);
			db2JdbcMap.put(DB2Type.CHAR, JDBCType.STRING);
			db2JdbcMap.put(DB2Type.CLOB, JDBCType.CLOB);
			db2JdbcMap.put(DB2Type.DATE, JDBCType.DATE);
			db2JdbcMap.put(DB2Type.DECIMAL, JDBCType.BIG_DECIMAL);
			db2JdbcMap.put(DB2Type.FLOAT, JDBCType.FLOAT);
			db2JdbcMap.put(DB2Type.INTEGER, JDBCType.INT);
			db2JdbcMap.put(DB2Type.NUMERIC, JDBCType.BIG_DECIMAL);
			db2JdbcMap.put(DB2Type.REAL, JDBCType.FLOAT);
			db2JdbcMap.put(DB2Type.TIME, JDBCType.TIME);
			db2JdbcMap.put(DB2Type.TIMESTAMP, JDBCType.TIMESTAMP);
			db2JdbcMap.put(DB2Type.VARCHAR, JDBCType.STRING);
		}
		return db2JdbcMap;
	}
	
	public String getJdbcValue(String type){
		if(db2JdbcMap == null){
			db2JdbcMap = getJdbcMap();
		}
		return db2JdbcMap.get(type);
	}
	
	public Map<String, String> getSimpleJavaMap(){
		if(db2JavaMap == null){
			db2JavaMap = new HashMap<String,String>();
			db2JavaMap.put(DB2Type.BIGINT, JavaSimpleType.LONG);
			db2JavaMap.put(DB2Type.BLOB, JavaSimpleType.BYTE_ARR);
			db2JavaMap.put(DB2Type.CHAR, JavaSimpleType.STRING);
			db2JavaMap.put(DB2Type.CLOB, JavaSimpleType.STRING);
			db2JavaMap.put(DB2Type.DATE, JavaSimpleType.SQL_DATE);
			db2JavaMap.put(DB2Type.DECIMAL, JavaSimpleType.BIG_DECIMAL);
			db2JavaMap.put(DB2Type.FLOAT, JavaSimpleType.DOUBLE);
			db2JavaMap.put(DB2Type.INTEGER, JavaSimpleType.Integer);
			db2JavaMap.put(DB2Type.NUMERIC, JavaSimpleType.BIG_DECIMAL);
			db2JavaMap.put(DB2Type.REAL, JavaSimpleType.FLOAT);
			db2JavaMap.put(DB2Type.TIME, JavaSimpleType.SQL_TIME);
			db2JavaMap.put(DB2Type.TIMESTAMP, JavaSimpleType.TIME_STAMP);
			db2JavaMap.put(DB2Type.VARCHAR, JavaSimpleType.STRING);
		}
		return db2JavaMap;
	}
	
	public String getJavaSimpleValue(String type){
		if(db2JavaMap == null){
			db2JavaMap = getSimpleJavaMap();
		}
		return db2JavaMap.get(type);
	}
}
