package update.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import update.dao.ClientBeanDao;
import update.dao.VersionDao;
import update.domain.ClientBean;
import update.domain.ClientMessage;
import update.domain.ServerMessage;
import update.domain.Version;
import update.service.VersionService;

//@Transactional
public class VersionServiceImpl implements VersionService {

	@Resource
	private VersionDao versionDao;
	@Resource
	private ClientBeanDao clientBeanDao;
	
	public boolean isExist(String ver){
		Version version = versionDao.findByVersion(ver);
		if(version == null)
			return false;
		return true;
	}

	@Override
	public Version fingByVer(String ver) {
		Version version = versionDao.findByVersion(ver);
		return version;
	}

	@Override
	public List<Version> list() {
		return versionDao.getList();
	}

	@Override
	public boolean useVersion(String ver) throws Exception {
		/*
		 * 1.建立socket
		 * 2.发送给updateServer
		 * 3.接收updateServer返回的数据
		 */
		
//		-----------------------------
//		Socket socket = null;
//		Properties prop = null;
//		try {
//			// web项目不能这样获取/src下的。因为/src后面会变成/WEB-INF/classes/下的文件
////			prop = new LoadProperties("versionManage.properties").getProperties();
//			
//			//使用getResourceAsStream。如果修改了配置文件要重启服务器，输入流的数据才会变化。
//			prop = new Properties();
//			InputStream in = this.getClass().getClassLoader().getResourceAsStream("versionManage.properties");
//			prop.load(in);
//			socket = new Socket(prop.getProperty("serverip"),
//					Integer.parseInt(prop.getProperty("serverport")));
//			-------------------------------------------------
			Socket socket = getSocket();
			// 只发送从页面接收过来的字符串所封装的对象
			ClientMessage cm = new ClientMessage();
			cm.setVersion(ver);
			cm.setManage(true);
			// 这里不需要多线程
			// 发送线程
//			threadPool.execute(new Send(socket,cm));
			// 接收线程
//			Receive receive = new Receive(socket);
//			threadPool.execute(receive);
			send(socket,cm);
			return receive(socket);
	}
	
	private void send(Socket socket,ClientMessage cm){
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(cm);
			out.flush();
			socket.shutdownOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean receive(Socket socket){

		InputStream in = null;
		ObjectInputStream objin = null;
		try {
			in = socket.getInputStream();
			objin = new ObjectInputStream(in);
			ServerMessage serverMessage = (ServerMessage) objin.readObject();
			if(serverMessage.getMsg().equalsIgnoreCase("success")){
				System.out.println("服务器的版本信息修改成功");
				return true;
			} else{
				System.out.println("服务器的版本信息修改失败");
				return false;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			try {
				if(objin != null)
				objin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean specificUseVersion(Integer cid, Integer vid) throws Exception {
		/*
		 * 1.根据主键获取cb和version，
		 * 2.把cb和version封装到clientMessage，设置manage=true。
		 * 3.通过Socket，使用ObjectOutputStream发送clientMessage对象给服务器。
		 *   服务器：
		 *   1.通过ObjectInputStream获取cb和version
		 *   2.修改配置文件（是XML了，不是properties）
		 *   	> 使XML中对应主键的cb的verid
		 *   	> 难点:
		 *   		1.现在是properties文件，要换成XML文件，需要用到XStream工具或者用DOM解析
		 *   		2.需要修改服务器端UpdateTask的判断。
		 *   			* manager = false  -> 客户端发来的信息
		 *   			* manager = true 
		 *   			  && clientbean(新增成员)==null&&versionBean(新增成员)==null  -> 统一更新
		 *   			* manager = true
		 *   			  && clientbean(新增成员)==null&&versionBean(新增成员)==null  -> 指定更新
		 *   3.等下次客户端发送信息给服务器就会发现版本不一样就进行更新。
		 * 3.通过ObjectInputStream获取服务器的反馈信息
		 */
		//1.
		ClientBean cb = clientBeanDao.get(cid);
		Version version = versionDao.get(vid);
		//2.
		ClientMessage clientMessage = new ClientMessage();
		clientMessage.setManage(true);
		clientMessage.setClientBean(cb);
		clientMessage.setVersionBean(version);
		
		//3. 
//		-------------------------
//		Socket socket = null;
//		Properties prop = null;
//		try {
//			prop = new Properties();
//			InputStream in = this.getClass().getClassLoader().getResourceAsStream("versionManage.properties");
//			prop.load(in);
//			socket = new Socket(prop.getProperty("serverip"),
//					Integer.parseInt(prop.getProperty("serverport")));
//		-----------------------------------
		Socket socket = getSocket();
		send(socket,clientMessage);
		return receive(socket);
	}
	
	private Socket getSocket(){
		Socket socket = null;
		Properties prop = null;
		try {
			prop = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("versionManage.properties");
			prop.load(in);
			socket = new Socket(prop.getProperty("serverip"),
					Integer.parseInt(prop.getProperty("serverport")));
			return socket;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Version version){
		versionDao.update(version);
	}
}
