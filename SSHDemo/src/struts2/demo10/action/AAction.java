package struts2.demo10.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import lqcUtils.CommonUtils;

/**
 * �ļ��ϴ�
 */
public class AAction {

	private String username;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//	1.���ý��ܵ����ԡ�����ͱ���name���Ե�ֵһ��
	private File uploadFile;
	private String uploadFileContentType;//MIME���ͣ����ֱ���Ϊ��������ContentType��
	private String uploadFileFileName;//�ļ������ƣ����ֱ���Ϊ��������FileName��
	
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
		 * ��������ϴ��ļ���С��
		 * <constant name="struts.multipart.maxSize" value="20971520"/>�Ϳ����ϴ�20M��С���ļ��ˡ�
		 */
		String realPath = ServletActionContext.getServletContext().getRealPath("/files");
		File dir = new File(realPath);//����������ļ���Ŀ¼
		if(!dir.exists()) dir.mkdirs();//�����ļ��У��༶Ŀ¼һ�𽨣�
		//commons-io��FileUtils
		File destFile =  new File(dir,CommonUtils.uuid()+"_"+uploadFileFileName);
		FileUtils.copyFile(uploadFile, destFile);
		System.out.println(uploadFileContentType);//ContentType
		System.out.println(uploadFileFileName);//�}ľ����-SAFEST PLACE.wma
		System.out.println(destFile.getAbsolutePath());
		//E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\webapps\SSHDemo\files\4D504C9388364F5BA8BE5175A762B433_�}ľ����-SAFEST PLACE.wma
		ActionContext.getContext().put("msg", "�ϴ��ɹ���");
		return "success";//return null��ʾ������jspҳ��
	}
}
