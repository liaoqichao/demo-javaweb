package demo1.client;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost",6789);// "localhost" -> "127.0.0.1"
		
		OutputStream out = socket.getOutputStream();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
		String str = "·þÎñÆ÷£¬ÄãbuºÃ£¡";
		bw.write(str);
		bw.flush();
		socket.shutdownOutput();
		bw.close();
		out.close();
		socket.close();
	}
}
