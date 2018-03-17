package framework.printer;

import framework.util.WritelnWriter;

public abstract class ConstructorPrinter {

	protected WritelnWriter ww;
	
	public ConstructorPrinter(WritelnWriter ww){
		this.ww = ww;
	}
	
	public abstract void print(String className) throws Exception;
}
