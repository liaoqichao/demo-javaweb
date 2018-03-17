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
			req.setCharacterEncoding("UTF-8");//����UTF-8����
			chain.doFilter(req, response);
		} else{
			/*
			 * ����get�������⡣
			 * ���⣺
			 * 1.��֪���������ơ�
			 * 2.requestû��setParameter����
			 * 
			 * �������������request��дrequest��װ���࣬��ǿgetParameter����������ʱʹ���Լ���request
			 */
			EncodingRequest er = new EncodingRequest(req);
			chain.doFilter(er, response);
			
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
