package demo1.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(6789);
		ExecutorService threadPool = Executors.newFixedThreadPool(2); //线程池固定有2个线程
		try{
			while(true){
				Socket s = ss.accept();  // 阻塞方法，只要没有客户端请求就不会继续执行。
				threadPool.execute(new HelloTask(s));
			}
		} catch(Exception e){
			throw new RuntimeException(e);
		} finally{
			if(threadPool != null) threadPool.shutdown();
			if(ss != null) ss.close();
		}
	}
}

class HelloTask implements Runnable{

	
	private Socket socket;
	
	public HelloTask(Socket socket){
		this.socket = socket;
	}
	@Override
	public void run() {
		InputStream in = null;
		BufferedReader br = null;
		try{
			
			in = socket.getInputStream();
			// 用输入流读取客户端的数据,该数据为字节数据
			br = new BufferedReader(new InputStreamReader(in)); 
			
			String line = "";
			int i = 0;
			while((line = br.readLine()) != null){
				i++;
				System.out.println("客户端说(第"+i+"行)："+line);
			}
		} catch(Exception e){
			throw new RuntimeException(e);
		} finally{
			try {
					if(br != null) br.close();
					if(in != null) in.close();
					if(this.socket != null) this.socket.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		}
	}
	
}
