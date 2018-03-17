package ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试拦截器1
 * @author Liaoqichao
 * @date 2016年5月10日
 */
public class HandlerInterceptor2 implements HandlerInterceptor {

	/*
	 *  执行Handler之后(返回完ModelAndView)执行(non-Javadoc)
	 *  应用场景：统一的异常处理，统一的日志处理
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HandlerInterceptor2#afterCompletion");
	}

	/*
	 *  进入Handler方法之后，返回ModelAndView方法之前执行
	 *  将共用的模型数据，在这里传到视图(比如：菜单的导航)。也可以在这里统一指定视图
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HandlerInterceptor2#postHandle");
		
	}

	/*
	 *  进入Handler方法之前执行
	 *  - 用于身份认证、身份授权，就是登陆认证和权限校验
	 *  - 例子:身份认证，如果当前用户没有登陆，需要此方法拦截，不向下执行。return false表示拦截，return true表示放行。
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {

		/*
		 * return true表示放行
		 * return false表示拦截
		 */
		System.out.println("HandlerInterceptor2#preHandle");
		return true;
	}

	
}
