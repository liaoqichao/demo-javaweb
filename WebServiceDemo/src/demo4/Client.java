package demo4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

/**
 * 使用UrlConnection方式调用WebService
 */
public class Client {

	public static void main(String[] args) throws Exception {
		// 服务地址
		URL wsUrl = new URL("http://127.0.0.1:6789/hello");
		
		// 得到URLConnection对象
		HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection(); //返回类型URLConnection
		
		conn.setDoInput(true); // 有输入
		conn.setDoOutput(true); // 有输出
		conn.setRequestMethod("POST"); // 设置请求方式
		conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8"); // 设置请求头
		
		OutputStream out = conn.getOutputStream();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
		
		
		// 请求体：原本的双引号要转义
		String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:q0=\"http://ws.demo2/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
		+"<soapenv:Body><q0:sayHello><arg0>aaa</arg0></q0:sayHello></soapenv:Body></soapenv:Envelope>";
		
		bw.write(soap); // 发送数据
		bw.flush();  // 不flush就报错！
		
		InputStream in = conn.getInputStream(); // 接收数据
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str  = "";
		String line = "";
		while((line = br.readLine())!= null){
			str = str+line;
		}
		System.out.println(str);
		
		// saxReader.read(br);报错。所以用这种方式
		Document document = DocumentHelper.parseText(str);
//		 怎么解析带有命名空间的标签？ 
		Node retNode = document.selectSingleNode("//*[local-name()='sayHelloResponse' and namespace-uri()='http://ws.demo2/']/return");
		String ret = retNode.getText();
		System.out.println(ret);
		// 关闭资源
		bw.close();
		br.close();
		conn.disconnect(); // 关闭连接
	}
}
