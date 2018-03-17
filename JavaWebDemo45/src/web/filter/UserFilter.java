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
		 * 1.�õ�session��username
		 * 2.�ж�session�е�username�ǰ�����admin
		 * 3.������ڣ�����
		 * 4.�ж�session���Ƿ����user
		 * 5.������ڷ���
		 * 6.�����ص�login.jsp
		 */
		//1.
		HttpServletRequest req = (HttpServletRequest)request;
		String username = (String) req.getSession().getAttribute("admin");//���ʱ����adminΪ��
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
			request.setAttribute("msg", "��ɶ�����ǲ�ҪϹ��");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
