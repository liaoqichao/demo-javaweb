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
 * User表述层
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();


	/**
	 * 注册功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String  regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
			1.封装表单数据到bean中
			2.补全，uid、code
			3.输入校验
				> 保存错误信息和form到request域，转发到regist.jsp
			4.调用service方法完成注册
				> 保存错误信息和form到request域，转发到regist.jsp
			5.发邮件
			6.保存成功信息转发到msg.jsp
		 */
		
//		1.封装表单数据到bean中
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
//		2.补全，uid、code
		form.setUid(CommonUtils.uuid());	//主键
		form.setCode(CommonUtils.uuid() + CommonUtils.uuid());	//激活码，64位
		/*
		 * 3.输入校验
		 * 	a. 创建一个Map用来封装错误信息，键为表单数字段名称，值为错误信息。 
		 * 	b. 获取form中的username，password，email进行校验
		 *  c.判断是否存在错误信息
		 */
		//a. 创建一个Map用来封装错误信息，键为表单数字段名称，值为错误信息。 
		Map<String,String> errors = new HashMap<String,String>();
//		b. 获取form中的username，password，email进行校验
		//校验用户名
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()){
			errors.put("username", "用户名不能为空！");
		} else if(username.length() < 3 || username.length() > 10){
			errors.put("username", "用户名长度必须在3~10之间！");
		}
		//校验密码
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()){
			errors.put("password", "密码不能为空");
		} else if(password.length() < 3 || password.length() > 10){
			errors.put("password", "密码长度必须在3~10之间！");
		}
		//校验邮箱
		String email = form.getEmail();
		if(email == null || email.trim().isEmpty()){
			errors.put("email", "Email不能为空");
		} else if(!email.matches("\\w+@\\w+\\.+\\w+")){//Pattern类，Match类？  格式：asd23@32d3.d32
			errors.put("email", "Email格式错误");
		}
		
		//c.判断是否存在错误信息	
		if(errors.size() > 0){
			request.setAttribute("errors", errors);	//保存错误信息到request域
			request.setAttribute("form", form);		//保存表单信息到request域
			return "f:/jsps/user/regist.jsp";		//转发到regist.jsp
		}
		
		/*
		 * 4.调用service方法完成注册
		 * 	> 保存错误信息和form到request域，转发到regist.jsp
		 */
		try {
			userService.regiset(form);
		} catch (UserException e) {	//如果service方法出现异常，说明用户名或者邮箱被使用。
//			保存异常信息到request域
			request.setAttribute("msg", e.getMessage());
//			保存表单信息到request域
			request.setAttribute("form", form);
//			转发到regist.jsp
			return "f:/jsps/user/regist.jsp";
		}
		
		/*
		 * 5.发邮件
		 * 	a.准备配置文件/src/email_template.properties
		 * 	b.获取配置文件内容
		 */
		//////////////////
		//b.加载配置文件
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		String host = new String(props.getProperty("host").getBytes("ISO-8859-1"),"UTF-8"); 		//获取服务器主机
		String uname = new String(props.getProperty("uname").getBytes("ISO-8859-1"),"UTF-8");		//获取用户名，发送邮件的邮箱
		String psw = new String(props.getProperty("psw").getBytes("ISO-8859-1"),"UTF-8");			//获取发送邮件的邮箱的密码
		String from = new String(props.getProperty("from").getBytes("ISO-8859-1"),"UTF-8");		//获取发件人
		String to = form.getEmail();					//获取收件人
		String subject = new String(props.getProperty("subject").getBytes("ISO-8859-1"),"UTF-8");	//获取主题
		String content = new String(props.getProperty("content").getBytes("ISO-8859-1"),"UTF-8");	//获取邮件内容
		content = MessageFormat.format(content, form.getCode());//替换content中的第一个{0}
		//properties中的{0},{1},{2}表示变量，0，1，2表示他们的顺序，是第几个变量
		//c.发邮件
		Session session = MailUtils.createSession(host, uname, psw);//得到session(连接)
		Mail mail = new Mail(from,to,subject,content);//创建邮件对象
		try {
			MailUtils.send(session, mail);				//发邮件
		} catch (MessagingException e) {
			//通常这里是邮件没发送成功，给别人重新发送的按钮的onclick事件添加监听器，把请求提交到MailServlet。单独一个MailServlet
			e.printStackTrace();
		}					
		/////////////////
		
		//		6.保存成功信息到request域，并转发到msg.jsp
		//到这里说明输入校验通过，业务逻辑里面的校验也通过，所以保存成功信息
		request.setAttribute("msg", "注册成功！请到邮箱激活！");
		//转发到msg.jsp
		return "f:/jsps/msg.jsp";
	}

	/**
	 * 激活功能：邮箱里面点击的超链接，把请求发送到UserServlet的这个方法
	 * properties中的{0}叫做占位符
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.邮箱中点击超链接，请求UserServlet#active
		//1.获取参数(GET请求)
		//2.使用激活码调用service#active(String code)方法
		//	> 保存异常信息到request中,跳到第四步
		//3.保存激活成功信息到request中
		//4.转发到msg.jsp
		
		//1.
		String code = request.getParameter("code");
		//2.
		try {
			userService.active(code);
			//3.
			request.setAttribute("msg", "激活成功！请马上登陆。");
		} catch (UserException e) {
			//	> 保存异常信息到request中
			request.setAttribute("msg", e.getMessage());
		}
		//4.
		return "f:/jsps/msg.jsp";
	}

	/**
	 * 登录
	 * 补：在登录的成功时，创建购物车到session域
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 1.封装表单数据到form中
		 * 2.输入校验
		 * 3.调用service完成登录
		 * 	> 如果抛出异常，保存form、异常信息到request中，转发到login.jsp
		 * 4.保存用户信息到session中，重定向到index.jsp
		 */
		//1.
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		String vcode = request.getParameter("vcode");//获取验证码
		//2.
		Map<String,String> errors = new HashMap<String,String>();
		//验证码校验，如果验证码错误，不提示用户名或密码错误。
		if(vcode==null || vcode.trim().isEmpty()){
			errors.put("vcode","验证码不能为空！");
		} else if(vcode.length() != 4){
			errors.put("vcode", "验证码的长度为4！");
		} else if(!vcode.equalsIgnoreCase((String) request.getSession().getAttribute("session_vcode"))){
			errors.put("vcode", "验证码错误!");
		}
		if(errors.size() > 0){
			//保存错误信息到request域
			request.setAttribute("errors",errors);
			//保存form到request域
			request.setAttribute("form", form);
			//转发到login.jsp
			return "f:/jsps/user/login.jsp";
			//转发到login.jsp是否会重新读取页面，访问VerifyCodeServlet，从而改变session_vcode的值?会！
		}
		//用户名校验
		if(form.getUsername()==null || form.getUsername().trim().isEmpty()){
			errors.put("username", "用户名不能为空");
		} else if(form.getUsername().length()<3 || form.getUsername().length()>10){
			errors.put("username","用户名长度必须在3~10之间");
		}
		//密码校验
		if(form.getPassword()==null || form.getPassword().trim().isEmpty()){
			errors.put("password", "密码不能为空");
		} else if(form.getPassword().length()<3 || form.getPassword().length()>10){
			errors.put("password","密码长度必须在3~10之间");
		}
		//判断map的长度
		if(errors.size() > 0){
			//保存错误信息到request域
			request.setAttribute("errors", errors);
			//保存form到request域
			request.setAttribute("form", form);
			//转发到/jsps/user/login.jsp
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
		request.getSession().invalidate();//先销毁session，提高安全性
		/*
		 * request.getSession(true);
		 * 如果session不为空，返回当前session，如果为空，新建session。不带参数默认为true
		 * 如果request.getSession(false);表示如果session为空不创建新session。
		 */
		request.getSession().setAttribute("session_user", user);//到这里登录成功！
		/*
		 * 补：在这里添加一个购物车，即向session保存一个Cart对象
		 */
		request.getSession().setAttribute("session_cart", new Cart());//如果在购物车模块没车就说明没有登录
		return "r:/index.jsp";
	}
	
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 例如论坛的发帖和积分，都是把要修改的信息，直接在session中的user修改，每隔3~5分钟同步到数据库一次。
		 * session销毁前再同步到数据库(监听器)。 
		 * 这里最简单直接销毁session。
		 * 安全性比较高的网站，在登录前先把session销毁再重新创建session
		 */
		request.getSession().invalidate();//销毁session
		return "r:/index.jsp";
	}
	
	
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "f:/jsps/book/desc.jsp";
	}
}
