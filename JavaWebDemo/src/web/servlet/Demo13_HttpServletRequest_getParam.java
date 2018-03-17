package web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示request获取请求参数(获取服务器收到的信息)
 * Servlet implementation class Demo13_HttpServletRequest_getParam
 */
@WebServlet("/demo13")
public class Demo13_HttpServletRequest_getParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo13_HttpServletRequest_getParam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GET :" + request.getParameter("xxx"));//XXX
		System.out.println("GET :" + request.getParameter("yyy"));//YYY
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String[]> params = request.getParameterMap();
		for(String name : params.keySet()){		//为什么Eclipse的foreach没有弹出这个 
			String[] value = params.get(name);
			System.out.println(Arrays.toString(value));
		}
//		Set<Map.Entry<String, String[]>> paramsSet = params.entrySet();
//		for(Iterator<Map.Entry<String,String[]>> it = paramsSet.iterator(); it.hasNext();){
//			//可以用foreach
//			Map.Entry<String, String[]> em = it.next();
////			String key = em.getKey();
//			String[] value = em.getValue();
//			System.out.print(Arrays.toString(value)+" ");
//		}
//		System.out.println();
		
		
//		String user = request.getParameter("user");
//		String psw =  request.getParameter("psw");
//		String[] hobbies = request.getParameterValues("hobbies");
//		
//		System.out.println(user+":"+psw+":"+Arrays.toString(hobbies));
	}

}
