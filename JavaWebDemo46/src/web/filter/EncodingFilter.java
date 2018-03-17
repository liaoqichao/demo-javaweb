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
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getMethod().equalsIgnoreCase("POST")){
			req.setCharacterEncoding("UTF-8");//处理UTF-8问题
			chain.doFilter(req, response);
		} else{
			/*
			 * 处理get请求问题。
			 * 问题：
			 * 1.不知道参数名称。
			 * 2.request没有setParameter方法
			 * 
			 * 解决方法：掉包request。写request的装饰类，增强getParameter方法。放行时使用自己的request
			 */
			EncodingRequest er = new EncodingRequest(req);
			chain.doFilter(er, response);
			
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
