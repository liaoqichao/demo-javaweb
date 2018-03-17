package struts2.demo10.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import lqcUtils.CommonUtils;

/**
 * 文件上传
 */
public class AAction {

	private String username;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//	1.设置接受的属性。必须和表单的name属性的值一样
	private File uploadFile;
	private String uploadFileContentType;//MIME类型，名字必须为“属性名ContentType”
	private String uploadFileFileName;//文件的名称，名字必须为“属性名FileName”
	
	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String test1() throws IOException{
		/*
		 * Request exceeded size limit!
		 * 如何设置上传文件大小？
		 * <constant name="struts.multipart.maxSize" value="20971520"/>就可以上传20M大小的文件了。
		 */
		String realPath = ServletActionContext.getServletContext().getRealPath("/files");
		File dir = new File(realPath);//创建保存的文件的目录
		if(!dir.exists()) dir.mkdirs();//创建文件夹（多级目录一起建）
		//commons-io的FileUtils
		File destFile =  new File(dir,CommonUtils.uuid()+"_"+uploadFileFileName);
		FileUtils.copyFile(uploadFile, destFile);
		System.out.println(uploadFileContentType);//ContentType
		System.out.println(uploadFileFileName);//倉木麻衣-SAFEST PLACE.wma
		System.out.println(destFile.getAbsolutePath());
		//E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\webapps\SSHDemo\files\4D504C9388364F5BA8BE5175A762B433_倉木麻衣-SAFEST PLACE.wma
		ActionContext.getContext().put("msg", "上传成功！");
		return "success";//return null表示不返回jsp页面
	}
}
