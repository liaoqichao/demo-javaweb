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
 * ����û�е�¼���û�
 */
@WebFilter(
		urlPatterns = { 
				"/jsps/cart/*", 
				"/jsps/order/*"
		}, 
		servletNames = { "bookstore.cart.web.sertvlet.CartServlet", "bookstore.cart.web.sertvlet.CartServlet"})
//ע������һ��Ҫдȫ������Ȼ���˲���
public class LoginFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * ���˵��û�û�е�¼���ܷ��ʵ�jsp��servlet��
	 * jsp:order�ļ����µģ�cart�ļ����µ�
	 * servlet��OrderServlet,CartServlet
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		/*
		 * 1.��session�л�ȡ�û���Ϣ
		 * 2.���session�д����û���Ϣ������
		 * 3.���򣬱��������Ϣת����login.jsp��ʾ
		 */
		System.out.println("����..");
		//1.
		HttpServletRequest httpRequest = (HttpServletRequest)request;//ǿת����
		User user = (User)httpRequest.getSession().getAttribute("session_user");
		//2.
		if(user != null){
			chain.doFilter(request, response);//����
		} else{	//3.
			httpRequest.setAttribute("msg", "����û�е�¼��");
			httpRequest.getRequestDispatcher("/jsps/user/login.jsp").forward(httpRequest, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
