package struts2.demo13.action;

import java.io.UnsupportedEncodingException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action�Ĺ��ʻ���
 */
public class AAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Action�Ĺ��ʻ�
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String test1() throws UnsupportedEncodingException{
		//��ȡ��Դ�ļ���key,�ڶ��������ַ�������Ϊռλ����ֵ
		String[] strs = new String[]{"����","Ьϰ"};
		for (int i = 0; i < strs.length; i++) {//����ISO����
			strs[i] = new String(strs[i].getBytes("UTF-8"),"ISO-8859-1");
		}
		String text = this.getText("welcome",strs);
		//��������������ЬϰUTF-8���룬ISO����,������Щ����ҪISO����
		text = new String(text.getBytes("ISO-8859-1"),"UTF-8");
		ActionContext.getContext().put("message", text);
		return "msg"; 
	}
}
