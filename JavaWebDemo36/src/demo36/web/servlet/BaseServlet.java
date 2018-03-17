package demo36.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��������class,����servlet.web.xml����������
 * @author Administrator
 *
 */
public abstract class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.��ȡ����������ʶ���û�������ķ�����
		String methodName = request.getParameter("method");
		if(methodName==null || methodName.trim().isEmpty()){
			throw new RuntimeException("û�д���method����");
		}
		//2.�õ���������ͨ���������õ�Method��Ķ���
		Class<? extends BaseServlet> c = this.getClass();
		Method method = null;
		try {
			method = c.getMethod(
					methodName,HttpServletRequest.class,HttpServletResponse.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("��Ҫ���õķ���"+methodName+"������");
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		//3.����method��ʾ�ķ�������һ��������ʵ�����ڶ��������Ǳ����䷽���Ĳ���
		try {
			String result = (String)method.invoke(this, request,response);//result�ĺ�׺Ӧ����һ��·��
			//4.��ȡ���󷽷�ִ�к󷵻ص��ַ���������ʾת�����ض��򡣸����ַ���ʵ��ת�����ض���
			if(result==null || result.trim().isEmpty())	return;
			if(!result.contains(":")){//���û��ð�ţ�Ĭ��Ϊת��
				request.getRequestDispatcher(result).forward(request, response);
			}else{
				//�����ð��,�ָ��ַ����õ�ǰ׺�ͺ�׺��ǰ׺Ϊ�ж��ض�����ת������׺Ϊ·��
				String[] res = result.split(":",2);//ֻ�ֳܷ�2���ַ�������,��ֹf:/a:bb/index.jsp
				
				if(res[0].equals("r")){
					response.sendRedirect(request.getContextPath()+res[1]);//�ض���
				} else if(res[0].equals("f")){
					request.getRequestDispatcher(res[1]).forward(request, response);
				} else{
					throw new RuntimeException("��ָ���Ĳ���"+res[0]+",��ǰ�汾����֧��");
				}
				
			}
		} catch (Exception e) {
			System.out.println(methodName+"�ڲ��������쳣");
			throw new RuntimeException(e);
		} 
	}
}
