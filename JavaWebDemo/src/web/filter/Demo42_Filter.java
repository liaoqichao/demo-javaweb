package web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 演示过滤器，第一个过滤器
 */
//@WebFilter("/Demo41_Filter")//
@WebFilter(filterName="/Demo41_Filter",urlPatterns="/demo42")
public class Demo42_Filter implements Filter {

	/**
	 * 销毁前执行，用来对非内存资源进行销毁
	 */
	public void destroy() {
//		System.out.println("过滤器要死了");
	}

	/**
	 * 每次过滤都会执行
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here

		// pass the request along the filter chain
//		System.out.println("拦截你");
		
//		System.out.println("A_start");
//		chain.doFilter(request, response);//相当于调用Servlet的service方法
//		System.out.println("A_end");
		
//		System.out.println("放行");
	}

	/**
	 * 初始化，创建后马上执行
	 */
	public void init(FilterConfig fConfig) throws ServletException {
//		System.out.println("过滤器出生");
	}

}
