

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
@WebServlet(asyncSupported = true, urlPatterns = "/AServlet")//��֧���첽����
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * Servlet���첽���������÷���������һ��������Ӧһ������������첽�Ļ��ͻ������ȫ��������֮��һ�ι���Ӧ��ҳ�档�������ʱ����������û�
	 * ��������װ壬�������ҳ���ǩתȦȦ(������)��Ȼ��˲��ȫ����ʾ�ꡣ���ʹ�����첽����ҳ������һ��һ����������ʾ.
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.���������������⣬�ᵼ���첽����ʧ�ܣ�
		response.setContentType("text/html;charset=utf-8");
		/*
		 * �����IE��������������������˵����Ӧ���С����512B��������Ҫ�������ϻ���
		 * for(int i=0 ; i<512 ;++){
		 * 	response.getWriter().print("a");
		 * }
		 * request.getWriter().flush();
		 */
		//1.�õ��첽�����Ķ���
		AsyncContext ac = request.startAsync(request, response);
		//2.��������һ��Runnable����
		ac.start(new Runnable(){
			public void run(){
				try {
					response.getWriter().print("������categoryServlet��runable�Ƕ��߳�Ҫʵ�ֵ�һ���ӿڣ��̻߳����run���������첽����<br/>");
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
					//֪ͨtomcat������߳��Ѿ�������
					/*
					 * ��Ϊservlet���̺߳�new Runnable���߳�˭�Ƚ�����ȷ����
					 * ���û����仰���������ȦȦ��һֱת��֪����ʱ��tomcatǿ�ƽ�����
					 * ����仰tomcat��֪������߳̽����ˣ�servlet���߳�Ҳ�����ˣ��ǾͶϿ�������������ӡ�
					 */
					ac.complete();
				}
				
			}
		});
		//3.���ֻ�������ᱨ500����not support����֧���첽����Ҫ��ע�������asyncSupported = true����֧���첽
	}



}
