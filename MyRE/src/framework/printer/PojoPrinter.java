package framework.printer;

import framework.db.Column;

public abstract class PojoPrinter extends Printer {

	public PojoPrinter(String pkg, String className, String tableName, Column col) {
		super(pkg, className, tableName, col);
	}

	abstract public void print() throws Exception;

}
