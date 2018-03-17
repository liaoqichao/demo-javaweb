package struts2.demo9.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class AAction {

	public String test1(){
		ActionContext ctx = ActionContext.getContext();//相当于得到request对象
		/*
		 * 下面3个put方法相当于对应域对象的setAttribute(String key,Object value);方法
		 */
		ctx.getApplication().put("application", "往application添加属性");
		ctx.getSession().put("session", "往session添加属性");
		ctx.put("request", "往request添加属性");
		System.out.println(ctx.getApplication().get("application"));
		System.out.println(ctx.getSession().get("session"));
		System.out.println(ctx.get("request"));
		return "success";
	}
	
	/**
	 * 获取request，response，servletContext，session,pageContext(四大域+响应)
	 * 应用场景。获取文件的真实路径
	 * application.getRealPath("");
	 * @return
	 */
	public String test2(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("request", "request-test2");
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		session.setAttribute("session", "session-test2");
		ServletContext application = request.getServletContext();
		application.setAttribute("application", "application-test2");
		return "success";
	}
}
