package struts2.demo1;

/**
 * 无入侵式设计：代码里面没有继承HttpServlet，没有request，response
 */
public class HelloAction {
	
	private String message;
	public String getMessage() {
		return message;
	}
	public String execute(){
		this.message = "我的第一个Struts2应用，hello world！";
		return "success";//这个是struts.xml中result的name
	}
	
}
