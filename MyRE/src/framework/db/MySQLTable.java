package framework.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import framework.util.JDBCUtils;

public class MySQLTable extends Table{

	public MySQLTable(String tableName) {
		super(tableName);
	}
	
	public Map<String,String> mapColAndType() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String sql = "DESC "+this.tableName;
		try(Connection conn = JDBCUtils.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();){
			while(rs.next()){
				map.put(rs.getObject(1).toString(), rs.getObject(2).toString());
			}
		} 
		return map;
	}

	@Override
	public Column getColumn() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Test
//	public void test() throws Exception{
//		this.tableName = "staff";
//		Map<String,String> map = mapColAndType();
//		for (Map.Entry<String, String> entry : map.entrySet()) {
//			System.out.println(entry.getKey()+" - " +entry.getValue());
//		}
//	}
	
}
