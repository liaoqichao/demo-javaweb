package update.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import update.domain.ServerMessage;
import update.domain.UpdateFile;
import update.domain.VerUpdate;

/**
 * 接收服务器端发来数据的线程
 * 可以使用线程，也可以使用receive方法
 */
public class ReceiveData implements Runnable{

	private Socket socket;
	private Properties prop;// 配置信息
//	private ServerMessage message; //封装发送给客户端的信息
	public ReceiveData(Socket socket, Properties prop){
		this.socket = socket;
		this.prop = prop;
	}
	
	public void receive(){
		InputStream in = null;
		GZIPInputStream gzipis = null; 
		ObjectInputStream objin = null;
		try {
					
			in = socket.getInputStream();
			gzipis = new GZIPInputStream(in);
			objin = new ObjectInputStream(gzipis);
//			objin = new ObjectInputStream(in);
//			message = (ServerMessage) objin.readObject();
			ServerMessage message = (ServerMessage) objin.readObject();
			if(message == null) {
				System.out.println("服务器还没发数据来呢！！收啥？");
				return ;
			}
			System.out.println("message :" +message.getMsg());
			if(message.getMsg().equalsIgnoreCase("same")){
				System.out.println("服务器和客户端版本相同，不做操作");
			} else if(message.getMsg().equalsIgnoreCase("different")){ //这里不能直接访问数据库获取更新版本，只能用服务器发过来的
				System.out.println("服务器和客户端版本不同，继续读取对象");
				changeUpdateStatus(true); // 改变更新状态为true，并且把信息写到prop文件
				// --- 方法一：所有内容放在一个对象，太占内存 ---
//				VerUpdate verUpdate = (VerUpdate)objin.readObject();
//				process(verUpdate);
				
				// --- 方法二：分成多个对象读取，单个文件最大为35M时依然不占内存 ---
				Set<UpdateFile> updateFileSet = new HashSet<UpdateFile>();
				for(int i = 0 ; i< message.getUpdateFileCount() ; i++){
					UpdateFile updateFile = (UpdateFile) objin.readObject();
					updateFileSet.add(updateFile);
				}
				process(message.getVer(),message.getServerVer(), updateFileSet); // 这里把状态改变为false
				
				// -- 方法三：GZIPIS装饰后再被OIS装饰
				
			} else if(message.getMsg().equalsIgnoreCase("updating")){
				System.out.println("客户端正在更新，不做操作！");
			} else{
				System.err.println("ServerMessage的message内容错误，只能为same或者different!");
			}
		} catch (Exception e) {
			changeUpdateStatus(false); // 修改状态为不在更新状态
			e.printStackTrace();
		} finally{	
			try {
				if(objin != null) objin.close();
				if(gzipis != null) gzipis.close();
				if(in != null) in.close();
				if(socket != null) socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

	/**
	 * 处理发送过来的更新信息。
	 * 1.如果进程开启，关闭主程序进程
	 * 2.获取对应的UpdateFile
	 * 3.根据UpdateFile覆盖主程序的文件
	 * 4.修改client.properties的版本号为VerUpdate的版本号
	 * 5.开启主程序
	 * @param verUpdate
	 */
	@SuppressWarnings("unused")
	private void process(VerUpdate verUpdate,String serverVer) {
		
		try {
//			1.如果进程开启，关闭主程序进程
			if(isRunning(prop.getProperty("main"))){
				closeProcess(prop.getProperty("main"));
				System.out.println("主程序进程已开启，需要关闭");
			}
			//2.获取对应的UpdateFile
			Set<UpdateFile> updateFileSet = verUpdate.getUpdateFileSet();
			//3.根据UpdateFile覆盖主程序的文件
			update(updateFileSet);
			//4.修改client.properties的版本号为VerUpdate的版本号
			updateProperties(verUpdate.get_version().getVer(),serverVer);
			//5.开启主程序
			System.out.println("主程序进程正在开启...");
			// 测试一下是不是关闭太快还没关完又开，所以开启不了。
			Thread.sleep(5000);
			runProgram(prop.getProperty("mainProgram"));
			System.out.println("主程序进程正在开启完毕");
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClientException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 处理发送过来的更新信息。
	 * 1.如果进程开启，关闭主程序进程
	 * 2.根据UpdateFile覆盖主程序的文件
	 * 3.修改client.properties的版本号为VerUpdate的版本号
	 * 4.开启主程序
	 * @param ver版本号字符串，updateFileSet更新的文件
	 */
	private void process(String ver ,String serverVer, Set<UpdateFile> updateFileSet) {
		
		try {
//			1.如果进程开启，关闭主程序进程
			if(isRunning(prop.getProperty("main"))){
				closeProcess(prop.getProperty("main"));
				System.out.println("主程序进程已开启，需要关闭");
			}
			//2.根据UpdateFile覆盖主程序的文件
			update(updateFileSet);
			//3.修改client.properties的版本号为VerUpdate的版本号
			updateProperties(ver,serverVer);
			//4.开启主程序
			System.out.println("主程序进程正在开启...");
			
			/*
			 *  判断更新到目标版本还是中途的一个版本
			 *  1. 如果更新到目标版本，则开启主程序
			 *  2. 如果更新到中途版本，不开启主程序
			 */
			// 测试一下是不是关闭太快还没关完又开，所以开启不了。
			if(prop.getProperty("version").equalsIgnoreCase(prop.getProperty("serverVersion"))){
				Thread.sleep(5000);
				runProgram(prop.getProperty("mainProgram"));
				System.out.println("主程序进程正在开启完毕");
			} else{
				System.out.println("还没更新完，关闭主程序后不用启动");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClientException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeFile(byte[] bytes,String path) throws ClientException{
		File file = new File(path);
		if(file.isDirectory()){   //路径错误（是文件夹）
			throw new ClientException("更新文件路径错误，不是文件夹，是文件！");
		}
		if(!file.exists()){		// 如果文件不存在，则创建
			try {
				//如果文件的目录不存在，则创建目录
				String dirStr = new String(path.substring(0, path.lastIndexOf("/")));
				File dir = new File(dirStr);
				if(!dir.exists()){
					dir.mkdirs();
				}
				//创建目录后再创建文件，这样就不会报错
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		//是文件，且存在
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(bytes);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭主程序
	 * @throws IOException
	 */
	private void closeProcess(String process) throws IOException{
		Runtime.getRuntime().exec("taskkill /F /IM "+process); //关闭主程序./F代表强制关闭
	}

	/**
	 * 更新。覆盖文件
	 * @param updateFileSet
	 * @throws ClientException
	 */
	private void update(Set<UpdateFile> updateFileSet) throws ClientException{
		String rootPath = prop.getProperty("rootPath"); //根路径
		for (UpdateFile updateFile : updateFileSet) {
			String path = rootPath+updateFile.getPath();  //文件的绝对路径
			System.out.println(path);
			writeFile(updateFile.getFile(),path);
		}
	}
	
	/**
	 * 修改client.properties的当前版本号和服务器记录该客户端的版本号
	 * @param newVersion 当前版本号
	 * @param serverVer 最终的版本号，服务器记录的版本号
	 */
	private void updateProperties(String newVersion,String serverVer){
		this.prop.setProperty("version", newVersion);
		try {
			FileWriter fw = new FileWriter("src/client.properties");
			prop.setProperty("version", newVersion);
			prop.setProperty("updating", "false"); // 状态为不在更新状态
			prop.setProperty("serverVersion", serverVer);
			//重写properties会丢失注释信息。
			prop.store(fw,"");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 改变更新状态，返回更改后的更新状态。
	 * 更新状态：正在更新和不在更新
	 * @param 改变到的状态，true表示正在更新
	 */
	private void changeUpdateStatus(boolean flag){
		if(flag){
			try {
				FileWriter fw = new FileWriter("src/client.properties");
				this.prop.setProperty("updating", "true"); // 状态为不在更新状态
				//重写properties会丢失注释信息。
				prop.store(fw,"");
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else{
			try {
				FileWriter fw = new FileWriter("src/client.properties");
				this.prop.setProperty("updating", "false"); // 状态为不在更新状态
				//重写properties会丢失注释信息。
				prop.store(fw,"");
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 开启主程序
	 * @param 要运行的程序，例如F:/QQ/QQ.exe
	 * @throws IOException 
	 */
	private void runProgram(String cmd) throws IOException{
		Runtime.getRuntime().exec(cmd);
	}
	
	/**
	 * 判断进程是否开启
	 * @param 进程名
	 * @return
	 */
	private boolean isRunning(String processName){
		BufferedReader br=null;   
        try{   
            Process proc = Runtime.getRuntime().exec("tasklist -fi " + '"' + "imagename eq " + processName +'"');   
            br = new BufferedReader(new InputStreamReader(proc.getInputStream()));   
            String line = null;   
            while((line = br.readLine())!=null){   
                //判断指定的进程是否在运行   
                if(line.contains(processName)){   
                    return true;   
                }   
            }   
                
            return false;   
        }catch(Exception e){   
        	throw new RuntimeException(e);
        }finally{   
            if(br!=null){   
                try{   
                    br.close();   
                }catch(Exception ex){
                	ex.printStackTrace();
                }   
            }   
                
        } 
	}

	/**
	 * 和receive代码一样
	 */
	@Override
	public void run() {
		receive();
//		InputStream in = null;
//		GZIPInputStream gzipis = null; 
//		ObjectInputStream objin = null;
//		try {
//					
//			in = socket.getInputStream();
//			gzipis = new GZIPInputStream(in);
//			objin = new ObjectInputStream(gzipis);
////			objin = new ObjectInputStream(in);
////			message = (ServerMessage) objin.readObject();
//			ServerMessage message = (ServerMessage) objin.readObject();
//			if(message == null) {
//				System.out.println("服务器还没发数据来呢！！收啥？");
//				return ;
//			}
//			if(message.getMsg().equalsIgnoreCase("same")){
//				System.out.println("服务器和客户端版本相同，不做操作");
//			} else if(message.getMsg().equalsIgnoreCase("different")){ //这里不能直接访问数据库获取更新版本，只能用服务器发过来的
//				System.out.println("服务器和客户端版本不同，继续读取对象");
//				
//				// --- 方法一：所有内容放在一个对象，太占内存 ---
////				VerUpdate verUpdate = (VerUpdate)objin.readObject();
////				process(verUpdate);
//				
//				// --- 方法二：分成多个对象读取，单个文件最大为35M时依然不占内存 ---
//				Set<UpdateFile> updateFileSet = new HashSet<UpdateFile>();
//				for(int i = 0 ; i< message.getUpdateFileCount() ; i++){
//					UpdateFile updateFile = (UpdateFile) objin.readObject();
//					updateFileSet.add(updateFile);
//				}
//				process(message.getVer(), updateFileSet);
//				
//				// -- 方法三：GZIPIS装饰后再被OIS装饰
//				
//			} else{
//				System.err.println("ServerMessage的message内容错误，只能为same或者different!");
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally{	
//			try {
//				if(objin != null) objin.close();
//				if(gzipis != null) gzipis.close();
//				if(in != null) in.close();
//				if(socket != null) socket.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//		}
	}
}
