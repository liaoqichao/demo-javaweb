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
 * ��ʾ������������������̺͹�������4�ֹ�������
 */
@WebFilter(urlPatterns = { "/demo42" }, servletNames = { "Demo42_Filter_Servlet" }
	,dispatcherTypes = { DispatcherType.FORWARD})
/*
 * urlPatterns���Թ���jsp��servlet����servletNamesֻ�ܹ���Serlvet������ServletNames��ֵ�ڱ����˵�Servlet����
 * ע��name���ԣ�ͨ�����name���Ե�ֵ���ң����Բ�һ��Ҫд���ȫ�ơ�
 */
//�ĳ�forward��A_start���ڵ�ַ������/demo42�����filter��ִ��doFilter��
//demo42..��ʾ��������Servlet
//A_end
//Demo44_Filter_DispatcherTypeͨ��ת����/demo42����ʱ�����������ִ��doFilter����Demo42_Filter��ִ��doFilter
//B_start
//demo42..��ʾ��������Servlet
//B_end
public class Demo43_MultiFilter implements Filter {

	public void destroy() {
//		System.out.println(this.getClass().getName()+"is destoryed");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

//		System.out.println("�ڶ���Filter����doFilter��");
		
//		System.out.println("B_start");
//		chain.doFilter(request, response);
//		System.out.println("B_end");
	}

	public void init(FilterConfig fConfig) throws ServletException {
//		System.out.println(this.getClass().getName()+"is started");
	}

}
