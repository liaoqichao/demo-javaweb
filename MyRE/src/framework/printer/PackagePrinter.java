package framework.printer;

import java.io.IOException;

import framework.util.WritelnWriter;

public class PackagePrinter {

	private WritelnWriter ww;
	
	public PackagePrinter(WritelnWriter ww){
		this.ww = ww;
	}
	
	public void print(String pkg) throws IOException{
		ww.writeln("package " + pkg + ";");
		ww.newLine();
	}
}
