package demo36.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建的是class,不是servlet.web.xml不会有配置
 * @author Administrator
 *
 */
public abstract class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.获取参数，用来识别用户想请求的方法。
		String methodName = request.getParameter("method");
		if(methodName==null || methodName.trim().isEmpty()){
			throw new RuntimeException("没有传递method参数");
		}
		//2.得到方法名，通过方法名得到Method类的对象
		Class<? extends BaseServlet> c = this.getClass();
		Method method = null;
		try {
			method = c.getMethod(
					methodName,HttpServletRequest.class,HttpServletResponse.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("您要调用的方法"+methodName+"不存在");
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		//3.调用method表示的方法。第一个参数是实例，第二个参数是被反射方法的参数
		try {
			String result = (String)method.invoke(this, request,response);//result的后缀应该是一个路径
			//4.获取请求方法执行后返回的字符串，它表示转发或重定向。根据字符串实现转发或重定向。
			if(result==null || result.trim().isEmpty())	return;
			if(!result.contains(":")){//如果没有冒号，默认为转发
				request.getRequestDispatcher(result).forward(request, response);
			}else{
				//如果有冒号,分割字符串得到前缀和后缀。前缀为判断重定向还是转发，后缀为路径
				String[] res = result.split(":",2);//只能分出2个字符串数组,防止f:/a:bb/index.jsp
				
				if(res[0].equals("r")){
					response.sendRedirect(request.getContextPath()+res[1]);//重定向
				} else if(res[0].equals("f")){
					request.getRequestDispatcher(res[1]).forward(request, response);
				} else{
					throw new RuntimeException("你指定的操作"+res[0]+",当前版本还不支持");
				}
				
			}
		} catch (Exception e) {
			System.out.println(methodName+"内部出现了异常");
			throw new RuntimeException(e);
		} 
	}
}
