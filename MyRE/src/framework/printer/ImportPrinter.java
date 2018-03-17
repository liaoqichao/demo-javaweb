package framework.printer;

import java.util.Set;

import framework.util.WritelnWriter;

public class ImportPrinter {

	protected WritelnWriter ww;
	
	public ImportPrinter(WritelnWriter ww){
		this.ww = ww;
	}

	public void print(Set<String> importSet) throws Exception {
		for (String string : importSet) {
			if(!string.startsWith("java.lang.")){
				ww.writeln("import " + string + ";");
			}
		}
		ww.newLine();
	}
}
