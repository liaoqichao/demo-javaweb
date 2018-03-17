package update.domain;

import java.io.Serializable;

/**
 * 服务器发送给客户端的信息。告诉版本是否相�?
 */
public class ServerMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	private int updateFileCount;
	private String ver; // 发送当前版本的版本号(中途版本)
	private String serverVer; // 服务器记录该客户端的版本号(最终版本)

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getUpdateFileCount() {
		return updateFileCount;
	}

	public void setUpdateFileCount(int updateFileCount) {
		this.updateFileCount = updateFileCount;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getServerVer() {
		return serverVer;
	}

	public void setServerVer(String serverVer) {
		this.serverVer = serverVer;
	}
	
}
