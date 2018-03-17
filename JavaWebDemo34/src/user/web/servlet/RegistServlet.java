package user.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myUtils.CommonUtils;
import user.domain.User;
import user.service.UserException;
import user.service.UserService;

/**
 * URL Mapping /RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//����POST����ı�������
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//����UserService
		UserService userService = new UserService();
		
		/*
		 * 0.ʹ��JavaScript���б�У��(�ͻ���)
		 * 1.��װ�����ݵ�user��
		 * 2.��У�顣�ڻ�ȡ������֮����֤��У��֮ǰ��Ȼ��У����֤�룬����������֮��
		 * 3.����userService#regist(form)
		 * 4.�õ��쳣,��ȡ�쳣��Ϣ,��װ��request��ת����regist����
		 * 5.û���쳣�����ע��ɹ���
		 */
		//1.
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		//2.�������˵ı�У��
			/*
			 * a.����һ��Mapװ���еĴ�����Ϣ��
			 * b.��У�������,���ʧ�ܣ���map��Ӵ�����Ϣ��keyΪ���ֶΡ�
			 * c.���map���ȴ���0��˵���д�����map��request�У�����form��request�С�ת����regist.jsp
			 * d.���map����Ϊ0��û������ִ�С�
			 */
		//2.a
		Map<String,String> errors = new HashMap<String,String>();//װ������Ϣ
		//2.b.���û�������У��
		String username = form.getUsername();//��ȡ����username
		if(username == null || username.trim().isEmpty()){
			errors.put("username", "�û�������Ϊ��");
		}
		if(username.length() < 3 || username.length()>15){
			errors.put("username", "�û������ȱ�����3~15֮��");
		}
		//���������У��
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()){
			errors.put("password", "���벻��Ϊ��");
		}
		if(password.length() < 3 || password.length()>15){
			errors.put("password", "���볤�ȱ�����3~15֮��");
		}
		//��status����У��
		String status = form.getStatus();
		if(status.contains("abc"))
			errors.put("status", "״̬���ܰ����ַ���abc");
		//����֤��У��
		String vCode1 = (String)request.getSession().getAttribute("session_code");//��ȷ����֤��
		String vCode2 = form.getVerifyCode();//�û��������֤��
		if(vCode2 ==null || vCode2.trim().isEmpty()){
			errors.put("verifyCode", "��֤�벻��Ϊ��");
		}
		else if(vCode2.length() != 4){
			errors.put("verifyCode", "��֤�볤�ȱ���Ϊ4");
		}else if(!vCode1.equalsIgnoreCase(vCode2)){
			errors.put("verifyCode", "��֤�����");
		}
		//2.c
		if(errors != null && errors.size()!=0){
			/*
			 * 1.����errors��request��
			 * 2.����form��request��
			 * 3.ת����regist.jsp
			 */
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		try {
			//3.
			userService.regist(form);
			//5.
			response.getWriter().print("<h1>ע��ɹ���</h1><br/>"
					+ "<a href='"+request.getContextPath()+"/user/login.jsp'>�������ȥ��¼</a>");
		} catch (UserException e) {
			//��form�����ݱ��浽request����,��<input input=text value="${requestScope.user.username}"/>
			//�������ݡ�
			request.setAttribute("user", form);
			//4.
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
		}
	}

}
