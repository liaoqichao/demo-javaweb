package struts2.demo11.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 自定义拦截器，拦截没有登录，不可以访问action
 */
public class Permission implements Interceptor {

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

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		String session_user = (String) ActionContext.getContext().getSession().get("user");
		if(session_user == null){//如果为空，说明没登录，要拦截
			ActionContext.getContext().put("msg", "您没有登录呢，别吓捣乱！");//保存没有登录的信息到request域。
		} else{	//允许执行action中的方法。
			ActionContext.getContext().put("msg", "你已经登录了，执行了action的方法！");
			arg0.invoke();//调用这个方法，被拦截到的方法就会被执行。不调用这个方法就不会被执行。
		}
		return "msg";//返回到/msg.jsp，最好定义成全局视图，要拦截很多action都要返回到这个视图。
	}

}
