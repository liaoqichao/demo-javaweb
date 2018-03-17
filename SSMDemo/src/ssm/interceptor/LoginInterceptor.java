package ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录认证拦截器
 * @author Liaoqichao
 * @date 2016年5月13日
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		/*
		 * 1. 获取请求url
		 * 2. 判断url是否是公开地址(实际使用时应该将公开地址配置当配置文件中)，这里公开地址就是登录提交的地址
		 * 3. 判断session
		 * 		- 如果session不存在，则跳转到登录页面。 拦截器没有ModelAndView，
		 * 			只能用request.getRequestDispatcher("/jsps/login.jsp").forward(request,response);
		 * 		- 如果session存在，则放行。
		 */
		
		//1.
		String url = request.getRequestURI();
		System.out.println(url);
		
		//2. 公开地址拦截
		if(url.indexOf("login.action")>=0){ // 包括"login.action字符串"
			return true;
		}
		
		//3. 
		HttpSession session = request.getSession();
		String session_username = (String) session.getAttribute("session_username");
		if(session_username != null){
			// 身份存在，已经登录过，放行。
			return true;
		} else{
			// 用户没有登录。跳转到登录页面
			
			// 设置提示信息：没有登录
			request.setAttribute("loginMsg", "您没有登录");
			request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
			return false;
		}
	}
}
