package template.printer;

import framework.db.Column;
import framework.printer.Printer;

public class ServicePrinter extends Printer{

	public ServicePrinter(String pkg, String className, String tableName, Column col) {
		super(pkg, className, tableName, col);
	}

	public void print() throws Exception {
		
	}

}
