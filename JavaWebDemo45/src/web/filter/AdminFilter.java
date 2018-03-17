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
		 * 1.�õ�session
		 * 2.�ж�session�����Ƿ����admin
		 * 3.������ڣ�����
		 * 4.��������ڣ���ص�index.jsp�������¼��Ϣ��request��
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
			request.setAttribute("msg", "�����ǹ���Ա,���ܵ�¼");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
