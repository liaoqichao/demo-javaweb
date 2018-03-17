package update.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import update.domain.ClientBean;
import update.domain.ClientMessage;
import update.domain.ServerMessage;
import update.domain.UpdateFile;
import update.domain.VerUpdate;
import update.domain.Version;
import update.service.ServerService;

/**
 * 更新服务器要执行的线程
 */
//@Transactional
public class UpdateTask implements Runnable {

	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	private Socket socket;
	private ServerMessage message = new ServerMessage();
	//为什么注入不了？
	private ServerService serverService = (ServerService) ctx.getBean("serverService");
	
	public UpdateTask(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {		//线程要执行的内容
		System.out.println(socket.getInetAddress().getHostAddress()+"已连接...");
		OutputStream out = null;	//发送给客户端的数据
		InputStream in = null;		//从客户端发来的数据
		GZIPOutputStream gzipos = null;
		ObjectOutputStream objout = null;
		try {
			in = socket.getInputStream(); //获取客户端发送过来的数据
			ClientMessage cm = serverService.getClientMessage(in); // 这里没关闭流！需要关闭吗？
			if(cm.isManage()){ // 管理员从web发来的数据
				if(cm.getClientBean()==null && cm.getVersionBean()==null){ //统一更新
					/*
					 * 1.修改prop的version属性的值(变成xml文件了)
					 * 2.重写到server.properties的version
					 * 3.回复信息
					 */
					//1,2
					serverService.modifyVersion(cm.getVersion());
					//3.
					this.message.setMsg("success");
					objout = new ObjectOutputStream(socket.getOutputStream());
					objout.writeObject(this.message);
					objout.flush();
				} else{ //指定更新
					//1. 修改配置文件（xml）客户端对应的版本号。
					serverService.modifyVersion(cm.getClientBean(),cm.getVersionBean().getVer());
					//2. 回复信息
					this.message.setMsg("success");
					objout = new ObjectOutputStream(socket.getOutputStream());
					objout.writeObject(this.message);
					objout.flush();
				}
			} else {	//客户端发来的数据
				// 客户端正在更新，这部分肯定执行不到，客户端已经有正在更新就不发送socket
				if(cm.getUpdating().equalsIgnoreCase("true")){ 
					System.out.println("客户端正在更新！！");
					this.message.setMsg("updating");
					out = socket.getOutputStream();
					gzipos = new GZIPOutputStream(out);
					objout = new ObjectOutputStream(gzipos);
					objout.writeObject(message);//不需要close，随着socket.close()，它会out.close()
					objout.flush();
					objout.close();
				} else{ // 客户端不在更新
					ClientBean cb = serverService.toClientBean(socket,cm);	
					
					//  ------从数据库中按clientname查找clientbean--------------
					/*
					 * SaveUpdate
					 * 按clientname查找数据库，
					 *  > 如果有,则获取其ip。
					 *  	> 如果ip相同,返回数据库中的对象
					 *      > 如果ip不同,修改数据库中的ip后,返回修改后的对象
					 *  > 如果没有,则插入一条clientbean记录
					 */
					serverService.saveOrUpdateClientBean(cb); //saveOrUpdate
					//  ---------------------------------------------------
						
					
					//  ----------判断服务器的当前版本是否和客户端的版本相同-------------
					String serverVersion = serverService.getServerVersion(cb);
					System.out.println("serverVersion : "+serverVersion);
					System.out.println("cb.get_version().getVer() : "+cb.get_version().getVer());
					if(serverVersion.equalsIgnoreCase(cb.get_version().getVer())){
						/*
						 * 如果版本相同，想客户端发送标识符，来表示版本相同
						 */
						System.out.println("服务器端：版本相同");
						message.setMsg("same");
						out = socket.getOutputStream();
						gzipos = new GZIPOutputStream(out);
						objout = new ObjectOutputStream(gzipos);
						objout.writeObject(message);//不需要close，随着socket.close()，它会out.close()
						objout.flush();
						objout.close();
					} else{
						/*
						 * 如果版本不同
						 * 1.从数据库获取server.properties记录的版本号的VerUpdate
						 * 2.发送到标识符（表示版本不同）和VerUpdate到客户端
						 */
						// --- 方法一：所有内容放在一个对象，太占内存 ---
//						System.out.println("服务器端：版本不同");
//						this.message.setMsg("different");
//						VerUpdate verUpdate = serverService.findVerUpdateByVersion(serverVersion);
//						out = socket.getOutputStream();
//						objout = new ObjectOutputStream(out);
//						objout.writeObject(message);
//						objout.writeObject(verUpdate);
//						objout.flush();
						
						// --- 方法二：分成多个对象读取，单个文件最大为35M时依然不太占内存 ---
//						System.out.println("服务器端：版本不同");
//						out = socket.getOutputStream();
//						objout = new ObjectOutputStream(out);
//						VerUpdate verUpdate = serverService.findVerUpdateByVersion(serverVersion);
//						// 发送message
//						this.message.setMsg("different");
//						this.message.setVer(verUpdate.get_version().getVer());
//						this.message.setUpdateFileCount(verUpdate.getUpdateFileSet().size());
//						objout.writeObject(message);
//						objout.flush();
//						// 发送多个UpdateFile
//						Set<UpdateFile> updateFileSet = verUpdate.getUpdateFileSet();
//						for (UpdateFile updateFile : updateFileSet) {
//							objout.writeObject(updateFile);
//							objout.flush();
//						}
						
						//  ---方法三:在方法二基础上再用GZIPOS装饰，压缩传输的对象----------------
						System.out.println("服务器端：版本不同");
						out = socket.getOutputStream();
						gzipos = new GZIPOutputStream(out);
						objout = new ObjectOutputStream(gzipos);
//						objout = new ObjectOutputStream(out);
						/*
						 * 这里开始改
						 */
						Version nextVersion = serverService.getNextVersion(cm.getVersion());
						VerUpdate verUpdate = null;
						if(nextVersion != null){
							verUpdate = serverService.findVerUpdateByVersion(nextVersion.getVer());
						} else{
							System.out.println("当前版本已经是最新的,现在变回之前的版本");
							verUpdate = serverService.findVerUpdateByVersion(serverVersion);
						}
						// 下面这句是原来的
//						VerUpdate verUpdate = serverService.findVerUpdateByVersion(serverVersion);
						/*
						 * 这里结束改
						 */
						// 发送message
						this.message.setMsg("different");
						this.message.setVer(verUpdate.get_version().getVer()); // 当前发送版本的版本号
						this.message.setUpdateFileCount(verUpdate.getUpdateFileSet().size());
						this.message.setServerVer(serverVersion); // 最终的版本号
						objout.writeObject(message);
						objout.flush();
						// 发送多个UpdateFile
						Set<UpdateFile> updateFileSet = verUpdate.getUpdateFileSet();
						for (UpdateFile updateFile : updateFileSet) {
							objout.writeObject(updateFile);
							objout.flush();
						}
						objout.close();  //没有这个的话 GZIPInputStream会报错EOF异常
					}
				}
			}
			//  ---------------------------------------------------
				
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServerException e) {
			System.err.println(e.getClass().getName()+": "+e.getMessage());
		} finally{
			try {
				if(in != null) in.close();
				if(gzipos != null) gzipos.close();
				if(out != null) out.close();
				if(objout != null) objout.close();
				if(socket != null) {
					System.out.println("服务器关闭与ip:"+socket.getInetAddress().getHostAddress()+"的连接");
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
//	private void write(OutputStream out, byte[] data) throws IOException{
//		for(int i=0 ; i<data.length/1024 ; i++){
//			if(i < data.length/1024){
////				byte[] temp = Arrays.copyOfRange(data, i*1024, (i+1)*1024); //应该是包括头部包括尾
//				out.write(data,i*1024,(i+1)*1024);
//				out.flush();
//			} else {
////				byte[] temp = Arrays.copyOfRange(data, i*1024, data.length);
//				out.write(data,i*1024,(i+1)*1024);
//				out.flush();
////				out.write(temp);
//			}
//		}
//	}
}
