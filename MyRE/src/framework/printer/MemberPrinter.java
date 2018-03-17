package framework.printer;

import framework.util.WritelnWriter;

public abstract class MemberPrinter {

	protected WritelnWriter ww;
	
	public MemberPrinter(WritelnWriter ww){
		this.ww = ww;
	}

}
