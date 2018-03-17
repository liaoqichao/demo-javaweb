package framework.printer;

import framework.db.Column;

public abstract class DaoPrinter extends Printer {

	public DaoPrinter(String pkg, String className, String tableName, Column col) {
		super(pkg, className, tableName, col);
	}

	@Override
	abstract public void print() throws Exception;

}
