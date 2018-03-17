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
	private String ver;

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
	
	
	
	
}
