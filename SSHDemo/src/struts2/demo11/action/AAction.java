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
		 * 被拦截了是不会执行这里的代码。
		 */
		this.message = "test1";
		System.out.println(message);
		return "msg";//返回到/msg.jsp视图
	}
	public String test2(){
		this.message = "test2";
		System.out.println(message);
		return "msg";
	}
}
