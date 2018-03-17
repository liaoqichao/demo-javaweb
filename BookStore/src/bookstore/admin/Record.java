package bookstore.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 单例模式，记录管理员操作到日志
 */
public class Record {

	private Record(){}//单例模式之饿汉式
	private static final Record record = new Record();
	public static Record newInstance(){
		return record;
	}
	/**
	 * 记录后台管理员的增删改到日志
	 * @param method
	 * @param order
	 */
	public void recordToAdminlog(String className,String method, Object object){
		//1.获取输出流
		String path = this.getClass().getClassLoader().getResource("/adminlog.txt").getPath();
		//E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\webapps\BookStore\WEB-INF\classes
		File file = new File(path);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));//这里的true表示不覆盖之前的内容
			PrintWriter pw = new PrintWriter(bw,true);
			String str = className+"_"+method+"_"+object+"_"+sdf.format(new Date());
			pw.println(str);
			pw.println();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
