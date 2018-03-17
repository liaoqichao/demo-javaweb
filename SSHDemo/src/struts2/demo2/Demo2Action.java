package struts2.demo2;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Demo2Action {

	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String execute() throws UnsupportedEncodingException{
		/*
		 * ������ڵ�ַ�����֣�����ҪURL���롣����ҳ���ȡ��${param.username}��URL�����ģ�����Ҫ����
		 */
		this.username = URLEncoder.encode("����","UTF-8");
		System.out.println(URLDecoder.decode("%E5%BC%A0%E4%B8%89", "UTF-8"));
		
		
//		this.username = "����";
		/*
		 * �����ַ���������������⡣
		 * ��<result></result>�������<param name="encode">true</param>
		 * 
		<action name="demo2" class="struts2.demo2.Demo2Action">
			<result type="redirect">
				<param name="location">/index.jsp?username=${username}</param>
				<param name="encode">true</param>
			</result>
		</action>
		
		 *
		 * ���ǲ��Ƽ���URL��ʹ�����ģ�����Ҫ��URL����
		 */
		return "success";
	}
	
	public String exec1(){
		
		/*
		 * ���ﲻ��success��execute()Ĭ�ϵ�<resulet name="success">.
		 * ����struts.xml�Ѿ�д��<result name="message">
		 */
		return "msg";
	}
}
