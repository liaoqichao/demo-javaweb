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
 * ����ģʽ����¼����Ա��������־
 */
public class Record {

	private Record(){}//����ģʽ֮����ʽ
	private static final Record record = new Record();
	public static Record newInstance(){
		return record;
	}
	/**
	 * ��¼��̨����Ա����ɾ�ĵ���־
	 * @param method
	 * @param order
	 */
	public void recordToAdminlog(String className,String method, Object object){
		//1.��ȡ�����
		String path = this.getClass().getClassLoader().getResource("/adminlog.txt").getPath();
		//E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\webapps\BookStore\WEB-INF\classes
		File file = new File(path);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));//�����true��ʾ������֮ǰ������
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
