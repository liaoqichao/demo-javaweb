package struts2.demo3;

/**
 * 为Action属性注入值(依赖注入)
 */
public class AAction {
	
	private String savePath;	//要在配置文件给他注入值就需要提供get、set方法

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	public String test1(){
		System.out.println(savePath);
		return "success";
	}

}
