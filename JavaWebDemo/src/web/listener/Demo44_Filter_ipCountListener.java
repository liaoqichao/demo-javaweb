package web.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 */
@WebListener
public class Demo44_Filter_ipCountListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    public void contextInitialized(ServletContextEvent arg0)  {
    	//创建Map
    	Map<String,Integer> map = new LinkedHashMap<String,Integer>();//为什么不用hashMap
    	//得到application,通过arg0获取事件源(观察者模式)
    	ServletContext application = arg0.getServletContext();
    	//把map保存到application中
    	application.setAttribute("map", map);
    	
    }
	
}
