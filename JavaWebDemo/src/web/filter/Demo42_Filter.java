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
 * ��ʾ����������һ��������
 */
//@WebFilter("/Demo41_Filter")//
@WebFilter(filterName="/Demo41_Filter",urlPatterns="/demo42")
public class Demo42_Filter implements Filter {

	/**
	 * ����ǰִ�У������Է��ڴ���Դ��������
	 */
	public void destroy() {
//		System.out.println("������Ҫ����");
	}

	/**
	 * ÿ�ι��˶���ִ��
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here

		// pass the request along the filter chain
//		System.out.println("������");
		
//		System.out.println("A_start");
//		chain.doFilter(request, response);//�൱�ڵ���Servlet��service����
//		System.out.println("A_end");
		
//		System.out.println("����");
	}

	/**
	 * ��ʼ��������������ִ��
	 */
	public void init(FilterConfig fConfig) throws ServletException {
//		System.out.println("����������");
	}

}
