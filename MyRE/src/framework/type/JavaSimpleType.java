package framework.type;

import java.util.HashSet;
import java.util.Set;

public final class JavaSimpleType {

	public static final String LONG = "long";
	public static final String BYTE_ARR = "byte[]";
	public static final String BOOLEAN = "boolean";
	public static final String STRING = "java.lang.String";
	public static final String SQL_DATE = "java.sql.Date";
	public static final String BIG_DECIMAL = "java.math.BigDecimal";
	public static final String DOUBLE = "double";
	public static final String Integer = "int";
	public static final String Object = "java.lang.Object";
	public static final String FLOAT = "float";
	public static final String SQL_TIME = "java.sql.Time";
	public static final String TIME_STAMP = "java.sql.TimeStamp";
	
	private static Set<String> set = new HashSet<String>();
	
	public static Set<String> getSimpleTypeSet(){
		set.add("int");
		set.add("byte");
		set.add("long");
		set.add("double");
		set.add("float");
		set.add("boolean");
		set.add("short");
		set.add("char");
		return set;
	}
}
