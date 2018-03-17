package demo7;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.WebService;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * 使用JaxWsServerFactoryBean方式发布WebService服务
 * @author Liaoqc
 */
@WebService // 必须加注解，不加的话服务可以发布，但是不暴露方法
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
