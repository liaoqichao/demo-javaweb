package framework.type;

import java.util.HashMap;
import java.util.Map;

public class JavaSimpleTypeMapping {

	public static final String LONG = "long";
	public static final String BYTE = "byte";
	public static final String BOOLEAN = "boolean";
	public static final String DOUBLE = "double";
	public static final String INTEGER = "int";
	public static final String FLOAT = "float";
	public static final String CHAR = "char";
	public static final String SHORT = "short";
	
	private static Map<String, String> map ;
	
	public static Map<String, String> getMap(){
		if(map == null){
			map = new HashMap<String, String>();
			map.put(LONG, "java.lang.Long");
			map.put(BYTE, "java.lang.Byte");
			map.put(BOOLEAN, "java.lang.Boolean");
			map.put(DOUBLE, "java.lang.Double");
			map.put(INTEGER, "java.lang.Integer");
			map.put(FLOAT, "java.lang.Float");
			map.put(CHAR, "java.lang.Character");
			map.put(SHORT, "java.lang.Short");
		}
		return map;
	}
	
	public static String getType(String simpleType){
		if(map == null){
			getMap();
		}
		return map.get(simpleType);
	}
	
}
