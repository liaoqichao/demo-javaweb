package struts2.demo11.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * �Զ���������������û�е�¼�������Է���action
 */
public class Permission implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		String session_user = (String) ActionContext.getContext().getSession().get("user");
		if(session_user == null){//���Ϊ�գ�˵��û��¼��Ҫ����
			ActionContext.getContext().put("msg", "��û�е�¼�أ����ŵ��ң�");//����û�е�¼����Ϣ��request��
		} else{	//����ִ��action�еķ�����
			ActionContext.getContext().put("msg", "���Ѿ���¼�ˣ�ִ����action�ķ�����");
			arg0.invoke();//������������������ص��ķ����ͻᱻִ�С���������������Ͳ��ᱻִ�С�
		}
		return "msg";//���ص�/msg.jsp����ö����ȫ����ͼ��Ҫ���غܶ�action��Ҫ���ص������ͼ��
	}

}
