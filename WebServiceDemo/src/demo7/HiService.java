package demo7;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.WebService;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * ʹ��JaxWsServerFactoryBean��ʽ����WebService����
 * @author Liaoqc
 */
@WebService // �����ע�⣬���ӵĻ�������Է��������ǲ���¶����
public class HiService {

	public static void main(String[] args) {
		JaxWsServerFactoryBean jwsfb = new JaxWsServerFactoryBean();
		
		jwsfb.setAddress("http://127.0.0.1:7788/hi");
		
		jwsfb.setServiceClass(HiService.class);
		
		jwsfb.setServiceBean(new HiService());
		
		jwsfb.create();
		System.out.println("Server ready...");
	}
	
	public String sayHi(String name){
		System.out.println("sayHi..name="+name);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date())+" :hi "+name;
	}
}
