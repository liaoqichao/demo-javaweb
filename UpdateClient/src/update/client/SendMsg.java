package update.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import update.domain.ClientMessage;

/**
 * 客户端发送信息的线程
 * 可以使用线程，也可以使用send方法
 */
public class SendMsg implements Runnable{

	private Socket socket ;  
	private ClientMessage cm;
	private Properties prop;
	// 这个构造方法给多线程的动
	public SendMsg(Socket socket, ClientMessage cm, Properties prop) {
		super();
		this.socket = socket;
		this.cm = cm;
		this.prop = prop;
	}

	public SendMsg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void send(){
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(cm);
			out.flush();
			socket.shutdownOutput();//不告诉服务器已经输出完，会报错。
			Thread.sleep(Long.parseLong(prop.getProperty("sendSleepTime")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// 不需要关闭，由接收线程来关闭socket
//		finally{
//			try {
//				if(out != null)
//					out.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}		
		
	}

	@Override
	public void run() {
		send();
//		ObjectOutputStream out = null;
//		try {
//			out = new ObjectOutputStream(socket.getOutputStream());
//			out.writeObject(cm);
//			out.flush();
//			socket.shutdownOutput();//不告诉服务器已经输出完，会报错。
//			Thread.sleep(Long.parseLong(prop.getProperty("sendSleepTime")));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally{
//			try {
//				if(out != null)
//					out.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}		
	}

}
