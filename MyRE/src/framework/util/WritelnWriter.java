package framework.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class WritelnWriter extends BufferedWriter{

	public WritelnWriter(Writer out) {
		super(out);
	}

	public void writetabln(String str,int i) throws IOException{
		writetab(str,i);
		super.newLine();
	}
	
	public void writetabln(String str) throws IOException{
		writetabln(str,1);
	}
	
	public void writeln(String str) throws IOException{
		writetabln(str,0);
	}
	
	public void writetab(String str) throws IOException{
		writetab(str,1);
	}
	
	public void writetab(String str,int i) throws IOException{
		String tab = "";
		if(i>0){
			for(int a=0; a<i; a++){
				tab+="\t";
			}
		}
		super.write(tab+str);
	}
	
}
