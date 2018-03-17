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
 * UserService
 * URL Mapping : /LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//����ģ��(���?)�������Զ�ɾ������ע�ͺ��Զ��ж�ӦGET/POST���޸ı���,�����½�JSP���Զ�����JSTL��core��
		//Eclipse��ô����ģ�壿MyEclipse��������
		request.setCharacterEncoding("utf-8");//�������POST
		response.setContentType("test/html;charset=utf-8");//��Ӧ����
		
		//����UserService
		UserService userService = new UserService();
		
		/*
		 * 1.��װ�����ݵ�User form��
		 * 	--->����
		 * 	> ��У��
		 * 		a.����Mapװ������Ϣ
		 * 		b.�ж��û����Ƿ�Ϊ�ջ���Ϲ���(3~15)
		 * 		c.�ж������Ƿ�Ϊ�ջ���Ϲ���(3~15)
		 * 		d.�ж���֤���Ƿ�Ϊ�ջ���Ϲ���
		 * 		e.���map���Ȳ�Ϊ0,��map���浽request���У���form���浽request���С���ת����login.jsp
		 * 2.����service��login������
		 * 	> ����׳��쳣����ȡ�쳣��Ϣ�����浽request���У�����form��request���У�ת����login.jsp
		 *  > ���û���쳣�����淵��ֵ��session��,�ض���welcome.jsp
		 */
		
		//1.
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		//��У��
		//a.
		Map<String,String> errors = new HashMap<String,String>();
		if(form.getUsername()==null || form.getUsername().trim().isEmpty()){
			errors.put("username", "�û�������Ϊ��");
		}
		if(form.getUsername().length()<3 || form.getUsername().length()>15){
			errors.put("username", "�û�������Ϊ3~15");
		}
		//b.
		if(form.getPassword()==null || form.getPassword().trim().isEmpty()){
			errors.put("password", "���벻��Ϊ��");
		}
		if(form.getPassword().length()<3 || form.getPassword().length()>15){
			errors.put("password", "���볤��Ϊ3~15");
		}
		//c.
		String vCode1 = (String)request.getSession().getAttribute("session_code");
		String vCode2 = form.getVerifyCode();
		if(vCode2==null || form.getVerifyCode().trim().isEmpty()){
			errors.put("verifyCode", "��֤�벻��Ϊ��");
		}
		else if(vCode2.length()!=4){
			errors.put("verifyCode", "��֤�볤��Ϊ4");
		}else if(!vCode2.equalsIgnoreCase(vCode1)){
			errors.put("verifyCode", "��֤�����");
		}
		if(errors != null && errors.size()!=0){
			request.setAttribute("user", form);
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
			return;
		}
		
		//2.
		try{
			User user = userService.login(form);
			request.getSession().setAttribute("sessionUser", user);//��JSP������У���Ƿ��½��
			response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
		}catch(UserException e){
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		}
	}

}
