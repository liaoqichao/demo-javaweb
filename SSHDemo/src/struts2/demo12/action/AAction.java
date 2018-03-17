package struts2.demo12.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ����У�飺������action����ָ��action�����ô��뷽ʽʵ�֣�����XML���÷�ʽʵ�֡�
 */
public class AAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String mobile;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String update(){
		System.out.println("update is running...");
		ActionContext.getContext().put("msg", "��Ϣ���³ɹ�");
		return "msg";
	}
	public String save(){
		System.out.println("save is running...");
		ActionContext.getContext().put("msg","��Ϣ����ɹ�");
		return "msg";
	}
	/**
	 * �̳�ActionSupport����дvalidate����������action�е����з�����������У�顣
	 */
//	@Override
//	public void validate() {
//		if(username == null || username.trim().isEmpty()){
//			addFieldError("username", "�û�������Ϊ��");//��һ�����������ֶ����ƣ��ڶ���������������Ϣ��
//		}
//		if(mobile == null){
//			addFieldError(mobile, "�ֻ��Ų���Ϊ��");
//		} else if(!mobile.matches("^1[358]\\d{9}$")){
////			Pattern.compile("^1[358]\\d{9}$").matcher(this.mobile).matches();//����boolean
//			/*
//			 * ^1��ʾ��ͷ����Ϊ1
//			 * d{9}$����9�����֣�Ȼ��ͽ�����^��ʾ��ͷ,$��ʾ��β��
//			 */
//			addFieldError(mobile, "�ֻ���ʽ����ȷ");
//		}
//	}
	
	/**
	 * validateXxx������ֻ��xxx������������У�顣�������������д�ġ�
	 */
	public void validateUpdate(){
		if(username == null || username.trim().isEmpty()){
			addFieldError("username", "�û�������Ϊ��");//��һ�����������ֶ����ƣ��ڶ���������������Ϣ��
		}
		if(mobile == null){
			addFieldError(mobile, "�ֻ��Ų���Ϊ��");
		} else if(!mobile.matches("^1[358]\\d{9}$")){
//			Pattern.compile("^1[358]\\d{9}$").matcher(this.mobile).matches();//����boolean
			/*
			 * ^1��ʾ��ͷ����Ϊ1
			 * d{9}$����9�����֣�Ȼ��ͽ�����^��ʾ��ͷ,$��ʾ��β��
			 */
			addFieldError(mobile, "�ֻ���ʽ����ȷ");
		}
	}
	
	
}
