package framework.template;

import framework.db.Column;
import framework.printer.DaoPrinter;
import framework.printer.PojoPrinter;
import template.printer.ServicePrinter;

public abstract class Template {

	protected PojoPrinter pojoPrinter;
	protected DaoPrinter daoPrinter;
	protected ServicePrinter servicePrinter;
	
	protected String pkg;
	protected String className;
	protected Column col;
	protected String tableName;
	
	public Template(String pkg, String className, Column col, String tableName) {
		this.pkg = pkg;
		this.className = className;
		this.col = col;
		this.tableName = tableName;
	}
	
	public void print() throws Exception{
		pojoPrinter.print();
		daoPrinter.print();
	}
	
}
