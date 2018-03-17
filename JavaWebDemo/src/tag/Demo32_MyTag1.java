package tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

/**
 * demo32 �Զ����ǩ1
 * @author Administrator
 *
 */
public class Demo32_MyTag1 implements SimpleTag{

	private  PageContext pageContext;//��׼����һ�����Ÿ��������,�������շ����������������ݡ�
	@SuppressWarnings("unused")
	private  JspFragment body;	//���ڿ�������
	
	/**
	 * ���е�setXxx()����������doTag()֮ǰ��Tomcat����
	 * ����doTag()����ʹ�ô�����ʹ�õĶ���,ǰ���Լ�Ҫ���(�ֲ��������Ա����)
	 */
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
//		EL���ʽͨ���������,��Ҫ��JSP�;Ŵ����ö����out,pageContextһ����9��
		JspWriter out = pageContext.getOut();
		out.print("Hello Tag");
	}

	@Override
	public JspTag getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setJspBody(JspFragment body) {
		// TODO Auto-generated method stub
		this.body = body;
	}

	@Override
	public void setJspContext(JspContext context) {
		// TODO Auto-generated method stub
		this.pageContext = (PageContext)context;
	}

	@Override
	public void setParent(JspTag arg0) {
		// TODO Auto-generated method stub
	}

}
