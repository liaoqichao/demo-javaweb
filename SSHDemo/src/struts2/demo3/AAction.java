package struts2.demo3;

/**
 * ΪAction����ע��ֵ(����ע��)
 */
public class AAction {
	
	private String savePath;	//Ҫ�������ļ�����ע��ֵ����Ҫ�ṩget��set����

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
