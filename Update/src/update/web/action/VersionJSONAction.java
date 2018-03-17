package update.web.action;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.transaction.annotation.Transactional;

import update.domain.Version;
import update.service.VersionService;

@Transactional
public class VersionJSONAction {

	@Resource
	private VersionService versionService;
	private String message;
	private String ver;
	private Version version;
//	public String getVer() {
//		return ver;
//	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	@JSON
	public Version getVersion() {
		return version;
	}
	public void setVersion(Version version) {
		this.version = version;
	}
	
	public String versionBean2(){
		Version version = versionService.fingByVer(ver);
		this.version = version;
//		return "showVersion";
		return "useVersion";
	}
	public String versionBean(){
		Version version = versionService.fingByVer(ver);
		this.version = version;
		return "showVersion";
//		return "useVersion";
	}
	@JSON
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String useVersion(){
		boolean flag;
		try {
			flag = versionService.useVersion(this.ver);
			if(flag){
				this.message = "操作成功。等客户端自动更新！";
			} else{
				this.message = "操作失败。原因不明！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.message = "异常，可能是更新系统服务器没有开启！";
		}
		return "useVersion";
	}
}
