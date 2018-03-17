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
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/admin/*")
public class AdminFilter implements Filter {


    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		/*
		 * 1.得到session
		 * 2.判断session域中是否存在admin
		 * 3.如果存在，放行
		 * 4.如果不存在，打回到index.jsp并保存登录信息到request域
		 */
		//1.
		HttpServletRequest req = (HttpServletRequest)request;
		String username = (String) req.getSession().getAttribute("admin");
		//2.
		if(username != null){
			//3.
			chain.doFilter(request, response);
			return;
		} else{
			//4.
			request.setAttribute("msg", "您不是管理员,不能登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
