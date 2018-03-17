package ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ��¼��֤������
 * @author Liaoqichao
 * @date 2016��5��13��
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		/*
		 * 1. ��ȡ����url
		 * 2. �ж�url�Ƿ��ǹ�����ַ(ʵ��ʹ��ʱӦ�ý�������ַ���õ������ļ���)�����﹫����ַ���ǵ�¼�ύ�ĵ�ַ
		 * 3. �ж�session
		 * 		- ���session�����ڣ�����ת����¼ҳ�档 ������û��ModelAndView��
		 * 			ֻ����request.getRequestDispatcher("/jsps/login.jsp").forward(request,response);
		 * 		- ���session���ڣ�����С�
		 */
		
		//1.
		String url = request.getRequestURI();
		System.out.println(url);
		
		//2. ������ַ����
		if(url.indexOf("login.action")>=0){ // ����"login.action�ַ���"
			return true;
		}
		
		//3. 
		HttpSession session = request.getSession();
		String session_username = (String) session.getAttribute("session_username");
		if(session_username != null){
			// ��ݴ��ڣ��Ѿ���¼�������С�
			return true;
		} else{
			// �û�û�е�¼����ת����¼ҳ��
			
			// ������ʾ��Ϣ��û�е�¼
			request.setAttribute("loginMsg", "��û�е�¼");
			request.getRequestDispatcher("/jsps/login.jsp").forward(request, response);
			return false;
		}
	}
}
