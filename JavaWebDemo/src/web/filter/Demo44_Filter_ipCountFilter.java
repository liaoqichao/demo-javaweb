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
 * ��IPͳ�ơ�ip,count  127.0.0.1,5  168.192.163.9,12
 * 
 * Listener���𴴽�Map
 * ��application�л�ȡmap����request�л�ȡ�ͻ���ǰ��ip��
 * ���map����ip����map.value++,����put(ip,i)
 */
@WebFilter("/*")
public class Demo44_Filter_ipCountFilter implements Filter {
	
	private FilterConfig fConfig;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//1.�õ�application�е�Map
//		request.getServletContext();//������������Ӧ����init�Ĳ����õ�fConfig��ͨ����Ա�������ڷ����䴫�ݡ�
		ServletContext application = fConfig.getServletContext();
		@SuppressWarnings("unchecked")
		Map<String,Integer> map = (Map<String, Integer>) application.getAttribute("map");
		
		//2.��request�л�ȡ��ǰ�ͻ��˵�ip
		String ip = request.getRemoteAddr();
		
		//3.�鿴map�Ƿ�������ip��Ӧ�ķ��ʴ�����������ڣ�����+1�ٱ����ȥ
		if(map.containsKey(ip)){
			int count = map.get(ip);
			count++;
			map.put(ip, count);
		} else{
		//4.��������ڣ�˵����һ�η��ʱ�վ�����÷��ʴ���Ϊ1
			map.put(ip, 1);
		}
		//5.��map�Żص�app�С�����Ҳ�У���Ϊ��ȡ�������á���!
		application.setAttribute("map", map);
		
		chain.doFilter(request, response);//�϶�����
	}

	/**
	 * �ڷ���������ʱ��ִ�д˷��������ұ�����ִֻ��һ�Ρ�
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		this.fConfig = fConfig;
	}

}
