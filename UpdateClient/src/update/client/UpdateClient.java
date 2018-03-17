package update.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import update.domain.ClientMessage;


/**
 * 更新系统客户端
 * 出现问题：eclipse修改文件时，是到那个文件的界面后才修改。所以每次更新后都是要点一下client.properties，
 * client.properties的内容才会更新，程序运行的结果才会正确。
 * 解决办法：Window->Preference->General->点击workspace，勾选Refresh using native hooks or polling
 */
public class UpdateClient {

	public static void main(String[] args) {
		
		Socket socket = null;
		Properties prop = null;
//		Thread sendThread = null;
//		Thread receiveThread = null;
		final int nThreads = 5; // 线程池的线程数
		// ----创建固定线程数线程池--- fixed表示固定线程数，参数为固定多少个的线程数
		ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		
		try {
			// --- 获取客户端的配置信息 -------
			while(true){
				prop = new LoadProperties("client.properties").getProperties();
				/*
				 * 判断是否正在更新，如果正在更新则不创建socket，发送客户端信息。
				 */
				if(prop.getProperty("updating").equalsIgnoreCase("false")){  //没有正在更新
					
						
					//--- 读取配置文件的服务器ip和端口 ----
					socket = new Socket(prop.getProperty("serverip"),
							Integer.parseInt(prop.getProperty("serverport")));
					// ----------------------------
					ClientMessage cm = createClientMessage(prop);//从配置文件获取要发送给服务器端的信息 
					
					/*
					 * 使用多线程
					 */
	//				 发送msg到服务器端-线程
	//				sendThread = new Thread(new SendMsg(socket,cm,prop),"Thread-Send");
	//				sendThread.start();
					// -- 使用线程池 --
					threadPool.execute(new SendMsg(socket,cm,prop));
					
	//				 接收服务器端发来的data-线程
	//				receiveThread = new Thread(new ReceiveData(socket,prop),"Thread-Receive");
	//				receiveThread.start();
					// -- 使用线程池 --
					threadPool.execute(new ReceiveData(socket,prop));
					
					/*
					 * 不用多线程
					 */
	//				new SendMsg(socket,cm,prop).send();
	//				new ReceiveData(socket,prop).receive();
					
				} else{	// 正在更新
					System.out.println("客户端正在更新，不向服务器建立socket链接，并发送和接收信息！");
				}
				Thread.sleep(Long.parseLong(prop.getProperty("mainSleepTime")));
				prop = null;
				System.gc();
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			// -- 执行完后关闭线程池  ---
			if(threadPool != null)
				threadPool.shutdown();
		}
	}
	
	/**
	 * 把客户端发送给服务器端的信息封装成对象
	 * @param prop
	 * @return
	 */
	private static ClientMessage createClientMessage(Properties prop){
		ClientMessage cm = new ClientMessage();
		cm.setClientname(prop.getProperty("clientname"));
		cm.setVersion(prop.getProperty("version"));
		cm.setUpdating(prop.getProperty("updating"));
		return cm;
	}
}
