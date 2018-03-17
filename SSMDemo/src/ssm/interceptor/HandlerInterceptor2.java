package ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ����������1
 * @author Liaoqichao
 * @date 2016��5��10��
 */
public class HandlerInterceptor2 implements HandlerInterceptor {

	/*
	 *  ִ��Handler֮��(������ModelAndView)ִ��(non-Javadoc)
	 *  Ӧ�ó�����ͳһ���쳣����ͳһ����־����
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HandlerInterceptor2#afterCompletion");
	}

	/*
	 *  ����Handler����֮�󣬷���ModelAndView����֮ǰִ��
	 *  �����õ�ģ�����ݣ������ﴫ����ͼ(���磺�˵��ĵ���)��Ҳ����������ͳһָ����ͼ
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HandlerInterceptor2#postHandle");
		
	}

	/*
	 *  ����Handler����֮ǰִ��
	 *  - ���������֤�������Ȩ�����ǵ�½��֤��Ȩ��У��
	 *  - ����:�����֤�������ǰ�û�û�е�½����Ҫ�˷������أ�������ִ�С�return false��ʾ���أ�return true��ʾ���С�
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {

		/*
		 * return true��ʾ����
		 * return false��ʾ����
		 */
		System.out.println("HandlerInterceptor2#preHandle");
		return true;
	}

	
}
