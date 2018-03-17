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
    	//����Map
    	Map<String,Integer> map = new LinkedHashMap<String,Integer>();//Ϊʲô����hashMap
    	//�õ�application,ͨ��arg0��ȡ�¼�Դ(�۲���ģʽ)
    	ServletContext application = arg0.getServletContext();
    	//��map���浽application��
    	application.setAttribute("map", map);
    	
    }
	
}
