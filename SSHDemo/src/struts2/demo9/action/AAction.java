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
		ActionContext ctx = ActionContext.getContext();//�൱�ڵõ�request����
		/*
		 * ����3��put�����൱�ڶ�Ӧ������setAttribute(String key,Object value);����
		 */
		ctx.getApplication().put("application", "��application�������");
		ctx.getSession().put("session", "��session�������");
		ctx.put("request", "��request�������");
		System.out.println(ctx.getApplication().get("application"));
		System.out.println(ctx.getSession().get("session"));
		System.out.println(ctx.get("request"));
		return "success";
	}
	
	/**
	 * ��ȡrequest��response��servletContext��session,pageContext(�Ĵ���+��Ӧ)
	 * Ӧ�ó�������ȡ�ļ�����ʵ·��
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
