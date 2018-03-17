package web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/users/*")
public class UserFilter implements Filter {


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		/*
		 * 1.得到session和username
		 * 2.判断session中的username是包含在admin
		 * 3.如果存在，放行
		 * 4.判断session中是否存在user
		 * 5.如果存在放行
		 * 6.否则打回到login.jsp
		 */
		//1.
		HttpServletRequest req = (HttpServletRequest)request;
		String username = (String) req.getSession().getAttribute("admin");//存的时候是admin为键
		//2.
		if(username != null){
			//3.
			chain.doFilter(request, response);
			return;
		}
		//4.
		username = (String) req.getSession().getAttribute("user");
		if(username != null){
			//5.
			chain.doFilter(request, response);
			return;
		} else{
			request.setAttribute("msg", "您啥都不是不要瞎溜达！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
