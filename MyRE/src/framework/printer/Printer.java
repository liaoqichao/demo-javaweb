package framework.printer;

import java.io.File;
import java.io.IOException;

import framework.db.Column;

public abstract class Printer {

	public static final String POJO = "";  
	public static final String SERVICE = "Service";  
	public static final String DAO = "Dao";  
	
	protected static final String BASE_PATH = System.getProperty("user.dir")+"/src/";
	protected String pkg;
	protected String className;
	protected String tableName;
	protected Column col;
	
	
	public Printer(String pkg, String className,String tableName,Column col){
		this.pkg = pkg;
		this.className = className;
		this.tableName = tableName;
		this.col = col;
	}
	
	public abstract void print() throws Exception;
	
	protected File createFile(String pkg, String className,String classType) throws IOException{
		File dir = new File(BASE_PATH+pkg);
		if(!dir.exists()){
			dir.mkdirs();
		}
		File file = new File(BASE_PATH+pkg,className+classType+".java");
		file.createNewFile();
		return file;
	}
}
