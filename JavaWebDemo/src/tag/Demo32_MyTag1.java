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
 * demo32 自定义标签1
 * @author Administrator
 *
 */
public class Demo32_MyTag1 implements SimpleTag{

	private  PageContext pageContext;//先准备好一个顶九个的域对象,用来接收服务器发过来的数据。
	@SuppressWarnings("unused")
	private  JspFragment body;	//后期可能有用
	
	/**
	 * 所有的setXxx()方法都会在doTag()之前被Tomcat调用
	 * 所以doTag()可以使用传过来使用的对象,前提自己要存好(局部变量变成员变量)
	 */
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
//		EL表达式通常用来输出,就要用JSP就九大内置对象的out,pageContext一个顶9个
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
