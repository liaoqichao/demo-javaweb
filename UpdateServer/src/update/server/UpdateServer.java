package update.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 更新系统的服务器
 */
public class UpdateServer {

	public static void main(String[] args) {
		
		ServerSocket ss = null;
		final int nThreads = 5; //线程池里的线程数
		// 使用固定线程的线程池。
		ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		try {
			// 获取端口
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("服务器开启..."+ format.format(new Date()));
			String path = "src/server.xml";
			SAXReader reader = new SAXReader();
			Document document = reader.read(path);
			Element root = document.getRootElement();
			Attribute portAtt = root.attribute("port");
			int port = Integer.parseInt(portAtt.getValue());
			//1.
			ss = new ServerSocket(port);
			
			while(true){	//实现多线程
				//2.
				Socket s = ss.accept();
//				Thread updateThread = new Thread(new UpdateTask(s));
//				updateThread.start();//开启线程
				// -- 使用线程池 --
				threadPool.execute(new UpdateTask(s));
				System.gc();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(ss != null){
					ss.close();
					System.out.println("服务器关闭");
				}
				if(threadPool != null)
					threadPool.shutdown();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
