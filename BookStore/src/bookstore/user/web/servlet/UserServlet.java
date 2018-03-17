package bookstore.user.web.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.cart.domain.Cart;
import bookstore.user.domain.User;
import bookstore.user.service.UserException;
import bookstore.user.service.UserService;
import lqcUtils.CommonUtils;
import lqcUtils.mail.Mail;
import lqcUtils.mail.MailUtils;
import lqcUtils.servlet.BaseServlet;

/**
 * User������
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();


	/**
	 * ע�Ṧ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String  regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
			1.��װ�����ݵ�bean��
			2.��ȫ��uid��code
			3.����У��
				> ���������Ϣ��form��request��ת����regist.jsp
			4.����service�������ע��
				> ���������Ϣ��form��request��ת����regist.jsp
			5.���ʼ�
			6.����ɹ���Ϣת����msg.jsp
		 */
		
//		1.��װ�����ݵ�bean��
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
//		2.��ȫ��uid��code
		form.setUid(CommonUtils.uuid());	//����
		form.setCode(CommonUtils.uuid() + CommonUtils.uuid());	//�����룬64λ
		/*
		 * 3.����У��
		 * 	a. ����һ��Map������װ������Ϣ����Ϊ�����ֶ����ƣ�ֵΪ������Ϣ�� 
		 * 	b. ��ȡform�е�username��password��email����У��
		 *  c.�ж��Ƿ���ڴ�����Ϣ
		 */
		//a. ����һ��Map������װ������Ϣ����Ϊ�����ֶ����ƣ�ֵΪ������Ϣ�� 
		Map<String,String> errors = new HashMap<String,String>();
//		b. ��ȡform�е�username��password��email����У��
		//У���û���
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()){
			errors.put("username", "�û�������Ϊ�գ�");
		} else if(username.length() < 3 || username.length() > 10){
			errors.put("username", "�û������ȱ�����3~10֮�䣡");
		}
		//У������
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()){
			errors.put("password", "���벻��Ϊ��");
		} else if(password.length() < 3 || password.length() > 10){
			errors.put("password", "���볤�ȱ�����3~10֮�䣡");
		}
		//У������
		String email = form.getEmail();
		if(email == null || email.trim().isEmpty()){
			errors.put("email", "Email����Ϊ��");
		} else if(!email.matches("\\w+@\\w+\\.+\\w+")){//Pattern�࣬Match�ࣿ  ��ʽ��asd23@32d3.d32
			errors.put("email", "Email��ʽ����");
		}
		
		//c.�ж��Ƿ���ڴ�����Ϣ	
		if(errors.size() > 0){
			request.setAttribute("errors", errors);	//���������Ϣ��request��
			request.setAttribute("form", form);		//�������Ϣ��request��
			return "f:/jsps/user/regist.jsp";		//ת����regist.jsp
		}
		
		/*
		 * 4.����service�������ע��
		 * 	> ���������Ϣ��form��request��ת����regist.jsp
		 */
		try {
			userService.regiset(form);
		} catch (UserException e) {	//���service���������쳣��˵���û����������䱻ʹ�á�
//			�����쳣��Ϣ��request��
			request.setAttribute("msg", e.getMessage());
//			�������Ϣ��request��
			request.setAttribute("form", form);
//			ת����regist.jsp
			return "f:/jsps/user/regist.jsp";
		}
		
		/*
		 * 5.���ʼ�
		 * 	a.׼�������ļ�/src/email_template.properties
		 * 	b.��ȡ�����ļ�����
		 */
		//////////////////
		//b.���������ļ�
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		String host = new String(props.getProperty("host").getBytes("ISO-8859-1"),"UTF-8"); 		//��ȡ����������
		String uname = new String(props.getProperty("uname").getBytes("ISO-8859-1"),"UTF-8");		//��ȡ�û����������ʼ�������
		String psw = new String(props.getProperty("psw").getBytes("ISO-8859-1"),"UTF-8");			//��ȡ�����ʼ������������
		String from = new String(props.getProperty("from").getBytes("ISO-8859-1"),"UTF-8");		//��ȡ������
		String to = form.getEmail();					//��ȡ�ռ���
		String subject = new String(props.getProperty("subject").getBytes("ISO-8859-1"),"UTF-8");	//��ȡ����
		String content = new String(props.getProperty("content").getBytes("ISO-8859-1"),"UTF-8");	//��ȡ�ʼ�����
		content = MessageFormat.format(content, form.getCode());//�滻content�еĵ�һ��{0}
		//properties�е�{0},{1},{2}��ʾ������0��1��2��ʾ���ǵ�˳���ǵڼ�������
		//c.���ʼ�
		Session session = MailUtils.createSession(host, uname, psw);//�õ�session(����)
		Mail mail = new Mail(from,to,subject,content);//�����ʼ�����
		try {
			MailUtils.send(session, mail);				//���ʼ�
		} catch (MessagingException e) {
			//ͨ���������ʼ�û���ͳɹ������������·��͵İ�ť��onclick�¼���Ӽ��������������ύ��MailServlet������һ��MailServlet
			e.printStackTrace();
		}					
		/////////////////
		
		//		6.����ɹ���Ϣ��request�򣬲�ת����msg.jsp
		//������˵������У��ͨ����ҵ���߼������У��Ҳͨ�������Ա���ɹ���Ϣ
		request.setAttribute("msg", "ע��ɹ����뵽���伤�");
		//ת����msg.jsp
		return "f:/jsps/msg.jsp";
	}

	/**
	 * ����ܣ������������ĳ����ӣ��������͵�UserServlet���������
	 * properties�е�{0}����ռλ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.�����е�������ӣ�����UserServlet#active
		//1.��ȡ����(GET����)
		//2.ʹ�ü��������service#active(String code)����
		//	> �����쳣��Ϣ��request��,�������Ĳ�
		//3.���漤��ɹ���Ϣ��request��
		//4.ת����msg.jsp
		
		//1.
		String code = request.getParameter("code");
		//2.
		try {
			userService.active(code);
			//3.
			request.setAttribute("msg", "����ɹ��������ϵ�½��");
		} catch (UserException e) {
			//	> �����쳣��Ϣ��request��
			request.setAttribute("msg", e.getMessage());
		}
		//4.
		return "f:/jsps/msg.jsp";
	}

	/**
	 * ��¼
	 * �����ڵ�¼�ĳɹ�ʱ���������ﳵ��session��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 1.��װ�����ݵ�form��
		 * 2.����У��
		 * 3.����service��ɵ�¼
		 * 	> ����׳��쳣������form���쳣��Ϣ��request�У�ת����login.jsp
		 * 4.�����û���Ϣ��session�У��ض���index.jsp
		 */
		//1.
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		String vcode = request.getParameter("vcode");//��ȡ��֤��
		//2.
		Map<String,String> errors = new HashMap<String,String>();
		//��֤��У�飬�����֤����󣬲���ʾ�û������������
		if(vcode==null || vcode.trim().isEmpty()){
			errors.put("vcode","��֤�벻��Ϊ�գ�");
		} else if(vcode.length() != 4){
			errors.put("vcode", "��֤��ĳ���Ϊ4��");
		} else if(!vcode.equalsIgnoreCase((String) request.getSession().getAttribute("session_vcode"))){
			errors.put("vcode", "��֤�����!");
		}
		if(errors.size() > 0){
			//���������Ϣ��request��
			request.setAttribute("errors",errors);
			//����form��request��
			request.setAttribute("form", form);
			//ת����login.jsp
			return "f:/jsps/user/login.jsp";
			//ת����login.jsp�Ƿ�����¶�ȡҳ�棬����VerifyCodeServlet���Ӷ��ı�session_vcode��ֵ?�ᣡ
		}
		//�û���У��
		if(form.getUsername()==null || form.getUsername().trim().isEmpty()){
			errors.put("username", "�û�������Ϊ��");
		} else if(form.getUsername().length()<3 || form.getUsername().length()>10){
			errors.put("username","�û������ȱ�����3~10֮��");
		}
		//����У��
		if(form.getPassword()==null || form.getPassword().trim().isEmpty()){
			errors.put("password", "���벻��Ϊ��");
		} else if(form.getPassword().length()<3 || form.getPassword().length()>10){
			errors.put("password","���볤�ȱ�����3~10֮��");
		}
		//�ж�map�ĳ���
		if(errors.size() > 0){
			//���������Ϣ��request��
			request.setAttribute("errors", errors);
			//����form��request��
			request.setAttribute("form", form);
			//ת����/jsps/user/login.jsp
			return "f:/jsps/user/login.jsp";
		}
		//3.
		User user = null;
		try {
			user = userService.login(form);
		} catch (UserException e) {
			request.setAttribute("form", form);
			request.setAttribute("msg", e.getMessage());
			return "f:/jsps/user/login.jsp";
		}
		//4.
		request.getSession().invalidate();//������session����߰�ȫ��
		/*
		 * request.getSession(true);
		 * ���session��Ϊ�գ����ص�ǰsession�����Ϊ�գ��½�session����������Ĭ��Ϊtrue
		 * ���request.getSession(false);��ʾ���sessionΪ�ղ�������session��
		 */
		request.getSession().setAttribute("session_user", user);//�������¼�ɹ���
		/*
		 * �������������һ�����ﳵ������session����һ��Cart����
		 */
		request.getSession().setAttribute("session_cart", new Cart());//����ڹ��ﳵģ��û����˵��û�е�¼
		return "r:/index.jsp";
	}
	
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * ������̳�ķ����ͻ��֣����ǰ�Ҫ�޸ĵ���Ϣ��ֱ����session�е�user�޸ģ�ÿ��3~5����ͬ�������ݿ�һ�Ρ�
		 * session����ǰ��ͬ�������ݿ�(������)�� 
		 * �������ֱ������session��
		 * ��ȫ�ԱȽϸߵ���վ���ڵ�¼ǰ�Ȱ�session���������´���session
		 */
		request.getSession().invalidate();//����session
		return "r:/index.jsp";
	}
	
	
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "f:/jsps/book/desc.jsp";
	}
}
