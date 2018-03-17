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
		 * 这个会在地址栏出现，所以要URL编码。但是页面获取的${param.username}是URL编码后的，还需要解码
		 */
		this.username = URLEncoder.encode("张三","UTF-8");
		System.out.println(URLDecoder.decode("%E5%BC%A0%E4%B8%89", "UTF-8"));
		
		
//		this.username = "张三";
		/*
		 * 解决地址栏上中文乱码问题。
		 * 在<result></result>里面添加<param name="encode">true</param>
		 * 
		<action name="demo2" class="struts2.demo2.Demo2Action">
			<result type="redirect">
				<param name="location">/index.jsp?username=${username}</param>
				<param name="encode">true</param>
			</result>
		</action>
		
		 *
		 * 但是不推荐在URL上使用中文，所以要用URL编码
		 */
		return "success";
	}
	
	public String exec1(){
		
		/*
		 * 这里不是success，execute()默认的<resulet name="success">.
		 * 但是struts.xml已经写了<result name="message">
		 */
		return "msg";
	}
}
