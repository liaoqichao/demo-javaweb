package web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * 演示ServletContextAttributeListener
 * HttpSessionAttributeListener和ServletRequestAttributeServlet一样
 * 都有增加属性，修改属性，移除属性3个方法
 */
//有这个就可以不用在web.xml中注册
@WebListener
public class Demo38_Listener implements ServletContextAttributeListener {


    public void attributeAdded(ServletContextAttributeEvent arg0)  { 

    	//往application中添加属性时自动调用。访问index.jsp时有设置属性，调用这个方法。
//    	System.out.println("您向application中添加了一个名为:" + arg0.getName() +",值为:"
//    			+ arg0.getValue() +"的属性");
    	
//    	您向application中添加了一个名为:org.apache.jasper.runtime.JspApplicationContextImpl,值为:org.apache.jasper.runtime.JspApplicationContextImpl@7998dff2的属性
//    	您向application中添加了一个名为:org.apache.jasper.compiler.ELInterpreter,值为:org.apache.jasper.compiler.ELInterpreterFactory$DefaultELInterpreter@f39e079的属性
    	//每次访问JSP都会有这2个属性
    }

    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
//    	System.out.println("attributeRemoved ," + arg0.getName()+" = "+arg0.getValue());
    }


    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
//    	System.out.println(arg0.getName() + "=" +arg0.getValue() + "新值:" 
//    			+ arg0.getServletContext().getAttribute(arg0.getName())); //xxx=abc新值:123
    	//arg0.getValue() 得到是修改前的值
    	//arg0.getServletContext().getAttribute(arg0.getName())得到的是修改后的值
    }
	
}
