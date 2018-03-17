package jquery.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

public class Demo22Action {

	private String username;
	private String psw;
	private String message;
	@JSON
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JSON
	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}
	@JSON
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String returnJSON(){
		System.out.println(this.username +":" + this.psw);
		if(this.username.trim().equals("zhangSan")){
			this.message = "�û���������";
		} else{
			this.message = "�û�������";
		}
		// ��Ϊ��ajax�ύ��������Ҫ���������ص�ֻ�Ǿֲ�ҳ�������ȫ��ҳ�棬��return��ͼ��ȫ��ҳ�档
		// ������Ҫ��JSON������ͼ����root���ԣ�ָ��Ҫ���ص��ַ���/ʵ��������ݣ���JSON��ʽ���ظ�ҳ��
		return "success";
	}
	
	public String returnXml() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(this.username.trim().equals("zhangSan")){
			this.message = "<result><message>�û���������</message></result>";
//			this.message = "<message>�û���������</message>";
		} else{
			this.message = "<result><message>�û�������</message></result>";
//			this.message = "<message>�û�������</message>";
		}
		response.getWriter().print(this.message);
		return null;
	}
	
	public String returnString() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(this.username.trim().equals("zhangSan")){
			this.message = "�û���������";
		} else{
			this.message = "�û�������";
		}
		response.getWriter().print(this.message);
		return null;
	}
	
	
}
