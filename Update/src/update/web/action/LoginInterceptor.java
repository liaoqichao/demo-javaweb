package update.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import update.domain.User;

/**
 * 方法拦截器
 * 登录拦截器，没有登录不能访问action的指定方法
 */
public class LoginInterceptor 
extends MethodFilterInterceptor{
//	implements Interceptor {
	//extends MethodFilterInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

//	@Override
//	public String intercept(ActionInvocation arg0) throws Exception {
//		User session_user = (User) ActionContext.getContext()//
//				.getSession()//
//				.get("session_user");
//		if(session_user == null ){ //没有登录要拦截
//			ActionContext.getContext().put("loginMsg", "没有登录，请登录后再操作！");
//			return "login";
//		} else{ // 登录了，放行
//			return arg0.invoke();
//		}
//	}

//	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {

		User session_user = (User) ActionContext.getContext()//
				.getSession()//
				.get("session_user");
		if(session_user == null ){ //没有登录要拦截
			ActionContext.getContext().put("loginMsg", "没有登录，请登录后再操作！");
			return "login";  // 返回不了视图
		} else{ // 登录了，放行
			return arg0.invoke();
		}
	}

}
