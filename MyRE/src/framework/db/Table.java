package framework.db;

public abstract class Table {

	protected String tableName;
	
	public Table(String tableName){
		this.tableName = tableName;
	}
	
	public abstract Column getColumn() throws Exception;
}
