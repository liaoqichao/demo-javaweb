package ssm.exception;

/**
 * ϵͳ�Զ����쳣��
 * @author Liaoqichao
 * @date 2016��5��8��
 */
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public CustomException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomException(String message) {  // ����Ϊ�쳣��Ϣ
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

	
}
