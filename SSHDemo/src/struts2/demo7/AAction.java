package struts2.demo7;

public class AAction {
	private String msg;
	

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String methodA(){
		this.msg = "methodAAAAA";
		return "success";
	}
	public String methodB(){
		this.msg = "methodBBBBB";
		return "success";
	}
}
