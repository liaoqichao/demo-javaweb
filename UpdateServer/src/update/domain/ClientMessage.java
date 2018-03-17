package update.domain;

import java.io.Serializable;

public class ClientMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clientname; //客户端用
	private String version;    //客户端用
	private ClientBean clientBean; //指定更新用
	private Version versionBean; //指定更新用
	private boolean manage; // 暂时没用
	private String updating;
	
	public String getUpdating() {
		return updating;
	}
	public void setUpdating(String updating) {
		this.updating = updating;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isManage() {
		return manage;
	}
	public void setManage(boolean manage) {
		this.manage = manage;
	}
	public ClientBean getClientBean() {
		return clientBean;
	}
	public void setClientBean(ClientBean clientBean) {
		this.clientBean = clientBean;
	}
	public Version getVersionBean() {
		return versionBean;
	}
	public void setVersionBean(Version versionBean) {
		this.versionBean = versionBean;
	}
	@Override
	public String toString() {
		return "ClientMessage [clientname=" + clientname + ", version=" + version + ", manage=" + manage + "]";
	}
	
}
