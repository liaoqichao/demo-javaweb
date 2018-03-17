package web.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 演示多个过滤器的拦截流程和过滤器的4种过滤类型
 */
@WebFilter(urlPatterns = { "/demo42" }, servletNames = { "Demo42_Filter_Servlet" }
	,dispatcherTypes = { DispatcherType.FORWARD})
/*
 * urlPatterns可以过滤jsp和servlet，而servletNames只能过滤Serlvet，而且ServletNames的值在被过滤的Servlet上有
 * 注解name属性，通过这个name属性的值才找，所以不一定要写类的全称。
 */
//改成forward后A_start，在地址栏访问/demo42，这个filter不执行doFilter了
//demo42..演示过滤器的Servlet
//A_end
//Demo44_Filter_DispatcherType通过转发到/demo42，这时候这个过滤器执行doFilter，而Demo42_Filter不执行doFilter
//B_start
//demo42..演示过滤器的Servlet
//B_end
public class Demo43_MultiFilter implements Filter {

	public void destroy() {
//		System.out.println(this.getClass().getName()+"is destoryed");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

//		System.out.println("第二个Filter运行doFilter啦");
		
//		System.out.println("B_start");
//		chain.doFilter(request, response);
//		System.out.println("B_end");
	}

	public void init(FilterConfig fConfig) throws ServletException {
//		System.out.println(this.getClass().getName()+"is started");
	}

}
