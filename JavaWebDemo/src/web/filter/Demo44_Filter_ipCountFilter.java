package web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 分IP统计。ip,count  127.0.0.1,5  168.192.163.9,12
 * 
 * Listener负责创建Map
 * 从application中获取map，从request中获取客户当前的ip。
 * 如果map中有ip，则map.value++,否则put(ip,i)
 */
@WebFilter("/*")
public class Demo44_Filter_ipCountFilter implements Filter {
	
	private FilterConfig fConfig;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//1.得到application中的Map
//		request.getServletContext();//不建议这样，应该用init的参数得到fConfig，通过成员变量来在方法间传递。
		ServletContext application = fConfig.getServletContext();
		@SuppressWarnings("unchecked")
		Map<String,Integer> map = (Map<String, Integer>) application.getAttribute("map");
		
		//2.从request中获取当前客户端的ip
		String ip = request.getRemoteAddr();
		
		//3.查看map是否存在这个ip对应的访问次数。如果存在，次数+1再保存回去
		if(map.containsKey(ip)){
			int count = map.get(ip);
			count++;
			map.put(ip, count);
		} else{
		//4.如果不存在，说明第一次访问本站，设置访问次数为1
			map.put(ip, 1);
		}
		//5.把map放回到app中。不放也行？因为获取的是引用。？!
		application.setAttribute("map", map);
		
		chain.doFilter(request, response);//肯定放行
	}

	/**
	 * 在服务器启动时就执行此方法，而且本方法只执行一次。
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		this.fConfig = fConfig;
	}

}
