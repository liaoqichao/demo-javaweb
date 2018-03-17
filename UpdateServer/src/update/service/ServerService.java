package update.service;

import java.io.InputStream;
import java.net.Socket;
import java.util.List;

import update.domain.ClientBean;
import update.domain.ClientMessage;
import update.domain.VerUpdate;
import update.domain.Version;
import update.server.ServerException;

public interface ServerService {

	//查询不用开启事务
	VerUpdate findVerUpdateByVersion(String version) throws ServerException;

	ClientBean toClientBean(Socket socket, ClientMessage cm) throws ServerException;

	void saveOrUpdateClientBean(ClientBean cb);

	Version findVersionByVer(String ver) throws ServerException;

	/**
	 * 获取指定客户端服务器的版本。
	 * @param cb
	 * @return
	 */
	String getServerVersion(ClientBean cb);

	/**
	 * 修改server.xml中对应clientBean的version值
	 * @param clientBean
	 * @param version
	 */
	void modifyVersion(ClientBean clientBean, String version);

	/**
	 * 修改配置文件中所有clientbean的version
	 * @param version
	 */
	void modifyVersion(String version);

	/**
	 * 获取客户端发送过来的对象
	 * @param in
	 * @return
	 */
	ClientMessage getClientMessage(InputStream in);

	/**
	 * 获取所有版本列表
	 * @return
	 */
	public List<Version> getVersionList();
	
	/**
	 * 获取当前版本的下一个版本，没有返回null
	 * @return
	 */
	public Version getNextVersion(String ver);
}