package web.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用ServletContext 获取资源路径
 * Servlet implementation class Demo6_ServletContext_getRealPath
 */
@WebServlet("/demo6")
public class Demo6_ServletContext_getRealPath extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo6_ServletContext_getRealPath() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		得到的是有盘符的路径：F:/xxx/xx/x	那么网络的资源呢？URL?
		String path = this.getServletContext().getRealPath("/index.jsp");//记住要斜杠
		//E:\Eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JavaWebDemo\index.jsp
		//得到路径就可以用File对象,然后就可以删除这个文件。也可以变成流对象,就可以输入输出(传输)
		System.out.println(path);
		
		InputStream is = this.getServletContext().getResourceAsStream("/index.jsp");//等于下面2句
//		String path = this.getServletContext().getRealPath("/index.jsp");
//		FileInputStream fis = new FileInputStream(path);
		is.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
