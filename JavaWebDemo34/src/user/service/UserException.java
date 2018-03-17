package user.service;

public class UserException extends Exception {

	/**
	 * 自定义异常类：只是给出父类的构造器即可，方便用来创造对象。直接SHIFT+AIT+S-->C-->OK
	 * 异常的区别只是在于异常信息,而异常类几乎没区别,就只有类名不同。
	 */
	private static final long serialVersionUID = 1L;

	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
