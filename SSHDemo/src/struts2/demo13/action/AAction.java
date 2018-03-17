package struts2.demo13.action;

import java.io.UnsupportedEncodingException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action的国际化。
 */
public class AAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Action的国际化
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String test1() throws UnsupportedEncodingException{
		//获取资源文件的key,第二个参数字符串数组为占位符的值
		String[] strs = new String[]{"张三","鞋习"};
		for (int i = 0; i < strs.length; i++) {//换成ISO编码
			strs[i] = new String(strs[i].getBytes("UTF-8"),"ISO-8859-1");
		}
		String text = this.getText("welcome",strs);
		//但是这里张三和鞋习UTF-8编码，ISO解码,所以这些中文要ISO编码
		text = new String(text.getBytes("ISO-8859-1"),"UTF-8");
		ActionContext.getContext().put("message", text);
		return "msg"; 
	}
}
