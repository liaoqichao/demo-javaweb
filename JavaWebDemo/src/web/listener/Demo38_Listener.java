package web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * ��ʾServletContextAttributeListener
 * HttpSessionAttributeListener��ServletRequestAttributeServletһ��
 * �����������ԣ��޸����ԣ��Ƴ�����3������
 */
//������Ϳ��Բ�����web.xml��ע��
@WebListener
public class Demo38_Listener implements ServletContextAttributeListener {


    public void attributeAdded(ServletContextAttributeEvent arg0)  { 

    	//��application���������ʱ�Զ����á�����index.jspʱ���������ԣ��������������
//    	System.out.println("����application�������һ����Ϊ:" + arg0.getName() +",ֵΪ:"
//    			+ arg0.getValue() +"������");
    	
//    	����application�������һ����Ϊ:org.apache.jasper.runtime.JspApplicationContextImpl,ֵΪ:org.apache.jasper.runtime.JspApplicationContextImpl@7998dff2������
//    	����application�������һ����Ϊ:org.apache.jasper.compiler.ELInterpreter,ֵΪ:org.apache.jasper.compiler.ELInterpreterFactory$DefaultELInterpreter@f39e079������
    	//ÿ�η���JSP��������2������
    }

    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
//    	System.out.println("attributeRemoved ," + arg0.getName()+" = "+arg0.getValue());
    }


    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
//    	System.out.println(arg0.getName() + "=" +arg0.getValue() + "��ֵ:" 
//    			+ arg0.getServletContext().getAttribute(arg0.getName())); //xxx=abc��ֵ:123
    	//arg0.getValue() �õ����޸�ǰ��ֵ
    	//arg0.getServletContext().getAttribute(arg0.getName())�õ������޸ĺ��ֵ
    }
	
}
