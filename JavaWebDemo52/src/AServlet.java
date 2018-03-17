

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = "/AServlet")//打开支持异步处理
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * Servlet的异步处理：就是让服务器处理一点请求，响应一点请求。如果不异步的话就会服务器全部处理完之后一次过响应到页面。如果处理时间过长会让用户
	 * 的浏览器白板，浏览器的页面标签转圈圈(加载中)，然后瞬间全部显示完。如果使用了异步就是页面内容一点一点慢慢地显示.
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.如果不处理编码问题，会导致异步处理失败！
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 如果是IE浏览器不能正常输出，这说明响应体大小不足512B，那你需要多输出点废话。
		 * for(int i=0 ; i<512 ;++){
		 * 	response.getWriter().print("a");
		 * }
		 * request.getWriter().flush();
		 */
		//1.得到异步上下文对象
		AsyncContext ac = request.startAsync(request, response);
		//2.给上下文一个Runnable对象
		ac.start(new Runnable(){
			public void run(){
				try {
					response.getWriter().print("这里是categoryServlet：runable是多线程要实现的一个接口，线程会调用run方法！！异步处理<br/>");
					response.getWriter().flush();
					Thread.sleep(2000);
					for(char c='A' ; c<='Z' ; c++){
						response.getWriter().print(c);
						response.getWriter().flush();
						Thread.sleep(250);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					//通知tomcat，这个线程已经结束了
					/*
					 * 因为servlet的线程和new Runnable的线程谁先结束不确定！
					 * 如果没有这句话，浏览器的圈圈会一直转，知道超时，tomcat强制结束。
					 * 有这句话tomcat就知道这个线程结束了，servlet的线程也结束了，那就断开与浏览器的链接。
					 */
					ac.complete();
				}
				
			}
		});
		//3.如果只是这样会报500错误，not support。不支持异步。需要在注解上添加asyncSupported = true，才支持异步
	}



}
