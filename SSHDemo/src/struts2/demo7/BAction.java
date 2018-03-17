package struts2.demo7;

public class BAction {
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String methodA(){
		this.msg = this.getClass().getSimpleName()+" # methodA";
		return "success";
	}
	
	public String methodB(){
		this.msg = this.getClass().getSimpleName()+" # methodB";
		return "success";
	}
}
