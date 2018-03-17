package update.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.transaction.annotation.Transactional;

import update.domain.Version;
import update.service.VersionService;

@Transactional
public class VersionAction {

	@Resource
	private VersionService versionService;
	private List<Version> versionList;
	private String ver;
	private Integer vid;//  verion主键
	private Integer cid;//	clientBean主键
	private String message; // 返回的信息
	@JSON
	public List<Version> getVersionList() {
		return versionList;
	}
	public void setVersionList(List<Version> versionList) {
		this.versionList = versionList;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	@JSON
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String list(){
		this.versionList = versionService.list();
		return "showVersion";
	}
	public String list2(){
		this.versionList = versionService.list();
		return "useVersion";
	}
	public String listc(){
		this.versionList = versionService.list();
		return "specificUpdate";
	}
	
	public String specificUseVersion(){
		boolean flag;
		try {
			flag = versionService.specificUseVersion(cid, vid);
			if(flag){
				this.message = "更改版本成功！";
				System.out.println(this.message);
			} else {
				this.message = "更改版本失败，原因不明！";
				System.out.println(this.message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.message = "异常，可能是更新系统服务器没有开启！";
		}
		return "specificUpdate";
	}

}
