package ssm.exception;

/**
 * 系统自定义异常类
 * @author Liaoqichao
 * @date 2016年5月8日
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

	public CustomException(String message) {  // 参数为异常信息
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
