package framework.db;

import java.util.Map;

public class Column {

	private Map<String,ColAttribute> keyMap;
	
	private Map<String,ColAttribute> colMap;

	public Map<String, ColAttribute> getKeyMap() {
		return keyMap;
	}

	public void setKeyMap(Map<String, ColAttribute> keyMap) {
		this.keyMap = keyMap;
	}

	public Map<String, ColAttribute> getColMap() {
		return colMap;
	}

	public void setColMap(Map<String, ColAttribute> colMap) {
		this.colMap = colMap;
	}
	
}
