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
 * ʹ��UrlConnection��ʽ����WebService
 */
public class Client {

	public static void main(String[] args) throws Exception {
		// �����ַ
		URL wsUrl = new URL("http://127.0.0.1:6789/hello");
		
		// �õ�URLConnection����
		HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection(); //��������URLConnection
		
		conn.setDoInput(true); // ������
		conn.setDoOutput(true); // �����
		conn.setRequestMethod("POST"); // ��������ʽ
		conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8"); // ��������ͷ
		
		OutputStream out = conn.getOutputStream();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
		
		
		// �����壺ԭ����˫����Ҫת��
		String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:q0=\"http://ws.demo2/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
		+"<soapenv:Body><q0:sayHello><arg0>aaa</arg0></q0:sayHello></soapenv:Body></soapenv:Envelope>";
		
		bw.write(soap); // ��������
		bw.flush();  // ��flush�ͱ���
		
		InputStream in = conn.getInputStream(); // ��������
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str  = "";
		String line = "";
		while((line = br.readLine())!= null){
			str = str+line;
		}
		System.out.println(str);
		
		// saxReader.read(br);�������������ַ�ʽ
		Document document = DocumentHelper.parseText(str);
//		 ��ô�������������ռ�ı�ǩ�� 
		Node retNode = document.selectSingleNode("//*[local-name()='sayHelloResponse' and namespace-uri()='http://ws.demo2/']/return");
		String ret = retNode.getText();
		System.out.println(ret);
		// �ر���Դ
		bw.close();
		br.close();
		conn.disconnect(); // �ر�����
	}
}
