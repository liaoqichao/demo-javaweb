package web.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 页面静态化：过滤BookServlet
 */
//@WebFilter(servletNames = {"BookServlet"})//没有过滤，现在知道啦，要全限定名
@WebFilter(urlPatterns="/BookServlet")
public class StaticFilter implements Filter {

	private ServletContext application ;//在Filter不要用request得到application,用init的参数得到application
	public void destroy() {}

	public void init(FilterConfig fConfig) throws ServletException {
		
		this.application = fConfig.getServletContext();
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		/*
		 * 1.第一次访问时，查找对应的html是否存在，如果存在重定向到html
		 * 2.如果不存在，生成html文件。需要装饰HttpServletResponse，修改getWriter。
		 * 		pw = new PrintWriter(path,"UTF-8");path为html文件路径
		 * 3.放行！response就会不断的out.write("HTML语言")，但是不是输出到show.jsp，而是输出到HTML文件(path)中
		 * 4.重定向到html中
		 */
		/*
		 * 一、获取category参数
		 * 	category有四种可能：
		 * 		null --> null.html
		 * 		1 --> 1.html
		 * 		2 --> 2.html
		 * 		3 --> 3.html
		 * html的保存路径，htmls目录下
		 */
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String category = request.getParameter("category");
		String htmlPage = category + ".html";//null + ".html"  == "null.html" 得到对应的文件名称
		String htmlPath = application.getRealPath("/htmls");//得到文件的存放路径，有盘符,不能用来重定向
		File destFile = new File(htmlPath,htmlPage);
//		System.out.println(destFile.getAbsolutePath());
		//E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JavaWebDemo47\htmls\1.html
		//1.
		if(destFile.exists()){//如果文件存在
//			重定向到这个文件
			res.sendRedirect(req.getContextPath() + "/htmls/" + htmlPage);//是 /htmls/
			return;
		} 
		/*
		 * 2.如果html不存在，生成html
		 * 	> 放行，流程会到show.jsp会输出，但是我们不要输出到show.jsp，而是输出html页面。
		 * 		只需要装饰response把response.getWriter()把流换了就可以
		 */
		//给出HTML存放的完整路径。pw = new PrintWriter(path,"UTF-8");已经在硬盘生成了文件。
		StaticResponse sr = new StaticResponse(res, destFile.getAbsolutePath());//这里要res，因为sr是继承HttpServletResponse
		//3.放行，掉包了响应对象。放行后PrintWriter就在html中疯狂的out.write("html语言");页面内容就已经生成完了
		chain.doFilter(req, sr);
		//4.重定向到html中
		res.sendRedirect(req.getContextPath()+ "/htmls/" + htmlPage);
		//为什么是res，不是response或者sr？。这里res和response一样。res和sr一样。所以都一样。
		
	}

}
