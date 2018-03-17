package framework.type;

import java.util.Map;

public abstract class DataBaseTypeMapping {

	abstract public Map<String, String> getJavaMap();
	
	abstract public String getJavaValue(String type);
	
	abstract public Map<String, String> getJdbcMap();
	
	abstract public String getJdbcValue(String type);
	
	abstract public Map<String, String> getSimpleJavaMap();
	
	abstract public String getJavaSimpleValue(String type);
}
