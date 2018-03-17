package web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1.演示HttpServletResponse的发送错误状态码sendError(int sc,String msg)和设置成功状态setStatus(int sc)
 * 2.演示HttpServletResponse发送响应头
 * 3.演示HttpServletResponse发送响应体
 * 
 * 案例：发送302重定向,并设置Location头:demo10_a,demo10_b;
 * Refresh头：可以理解为定时重定向
 * 
 * Servlet implementation class Demo9_HttpServletResponse
 */
@WebServlet("/demo9")
public class Demo9_HttpServletResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo9_HttpServletResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
		 * 发送状态码
		 */
//		response.sendError(404,"你访问的资源存在,就不给你看！");//直接会在网页显示404
		
		/**
		 * 发送响应头
		 */
		/*
		 * 	setHeader(String name,String value);	//带set的是适用于单值的响应头。这个方法最常用*****
		 *	addHeader(String name,String value);	//带add的是适用于多值的响应头
		 *	setIntHeader(String name,int value);	//int类型的响应头。例如响应长度,浏览器要知道服务器发过来的东西有多少个字节
		 *	addIntHeader(String name,int value);
		 *	setDateHeader(String name,long value);//毫秒类型的响应头
		 *	addDateHeader(String name,long value);
		 */
//		response.setIntHeader("Content-Length", 888);	//告诉浏览器响应体的长度是888字节
//		response.setDateHeader("expires", 1000*60*60*24);	//页面过期时间为24小时。开发阶段需要经常改页面,所以后面需要它给浏览器禁用缓存
		//页面过期时间：在这个时间内如果再次访问该网页,不会访问服务器,直接在缓存里面加载页面。这个时间后只能访问服务器要页面
		
		/**
		 * 演示定时刷新
		 * 1.设置Refresh头：表示定时刷新
		 */
		/*
		 * 下面是用来发送响应体
		 */
//		PrintWriter writer = response.getWriter();
//		writer.print("欢迎xxx登录！3秒钟后会自动跳转到主页,你看到的一定是乱码");
//		/*
//		 * 设置Refresh的响应头
//		 */
//		response.setHeader("refresh", "3;URL=/JavaWebDemo/demo10_a");
		
		/**
		 * 禁用浏览器缓存
		 * 3句在一起
		 * 可以在jsp或者http的<head></head>中添加
		 * <meta http-equiv="pragma" context="no-cache"/>	<!--不区分大小写-->
		 * <meta http-equiv="cache-ontrol" context="no-cache"/>
		 * <meta http-equiv="expires" context="-1"/>
		 * 代替响应头
		 * <meta http-equiv="响应头的键" context="响应头的值"/>
		 */
//		response.setHeader("Cache-Control", "no-cache");
//		response.setHeader("pragma", "no-cache");
//		response.setDateHeader("expires", -1);
		
		/**
		 * 发送响应体
		 * response有2个流
		 * PrintWriter out = response.getWriter();					//字符流
		 * ServletOutputStream out = response.getOutputStream();	//字节流
		 * 这2个方法不能同时使用,不然会抛出IllegalStateException （继承RuntimeException）
		 * **不能先得到一个流,然后关闭,再得另外一个类
		 */
//		PrintWriter out = response.getWriter();
//		out.write("Hello world! demo9");
//		out.close(); 不用关,response会帮你关
		ServletOutputStream out = response.getOutputStream();
		File file = new File("E:\\Eclipse\\IO\\ByteStream\\demo2.jpg");
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = fis.read(buf)) != -1){
			out.write(buf, 0, len);
		}
		fis.close();
	}

}
