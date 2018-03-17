package framework.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import framework.util.JDBCUtils;

public class DB2Table extends Table{

	
	public DB2Table(String tableName){
		super(tableName);
	}
	
	public Column getColumn() throws Exception{
		Column col = new Column();
		Map<String, ColAttribute> keyMap = new HashMap<String, ColAttribute>();
		Map<String, ColAttribute> colMap = new HashMap<String, ColAttribute>();
		String sql = "select name,coltype,keyseq,remarks from sysibm.syscolumns where tbname = ?";
		try(Connection conn = JDBCUtils.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);){
			pst.setString(1, this.tableName.toUpperCase());
			try(ResultSet rs = pst.executeQuery()){
				while(rs.next()){
					ColAttribute attr = new ColAttribute();
					attr.setType(rs.getString("coltype").trim());
					attr.setRemarks(rs.getString("remarks"));
					if(rs.getInt("keyseq") != 0){
						keyMap.put(rs.getString("name").toLowerCase(), attr);
					} else{
						colMap.put(rs.getString("name").toLowerCase(), attr);
					}
				}
			}
		} 
		col.setKeyMap(keyMap);
		col.setColMap(colMap);
		return col;
	}
	
}
