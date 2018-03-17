package bookstore.user.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import bookstore.user.domain.User;

/**
 * 过滤没有登录的用户
 */
@WebFilter(
		urlPatterns = { 
				"/jsps/cart/*", 
				"/jsps/order/*"
		}, 
		servletNames = { "bookstore.cart.web.sertvlet.CartServlet", "bookstore.cart.web.sertvlet.CartServlet"})
//注解这里一定要写全名。不然过滤不了
public class LoginFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * 过滤掉用户没有登录不能访问的jsp和servlet。
	 * jsp:order文件夹下的，cart文件夹下的
	 * servlet：OrderServlet,CartServlet
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		/*
		 * 1.从session中获取用户信息
		 * 2.如果session中存在用户信息，放行
		 * 3.否则，保存错误信息转发到login.jsp显示
		 */
		System.out.println("拦截..");
		//1.
		HttpServletRequest httpRequest = (HttpServletRequest)request;//强转类型
		User user = (User)httpRequest.getSession().getAttribute("session_user");
		//2.
		if(user != null){
			chain.doFilter(request, response);//放行
		} else{	//3.
			httpRequest.setAttribute("msg", "您还没有登录！");
			httpRequest.getRequestDispatcher("/jsps/user/login.jsp").forward(httpRequest, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
