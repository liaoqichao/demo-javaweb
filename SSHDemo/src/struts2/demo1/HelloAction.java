package struts2.demo1;

/**
 * ������ʽ��ƣ���������û�м̳�HttpServlet��û��request��response
 */
public class HelloAction {
	
	private String message;
	public String getMessage() {
		return message;
	}
	public String execute(){
		this.message = "�ҵĵ�һ��Struts2Ӧ�ã�hello world��";
		return "success";//�����struts.xml��result��name
	}
	
}
