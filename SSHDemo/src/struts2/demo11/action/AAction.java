package struts2.demo11.action;

public class AAction {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String test1(){
		/*
		 * ���������ǲ���ִ������Ĵ��롣
		 */
		this.message = "test1";
		System.out.println(message);
		return "msg";//���ص�/msg.jsp��ͼ
	}
	public String test2(){
		this.message = "test2";
		System.out.println(message);
		return "msg";
	}
}
