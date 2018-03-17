package template;

import framework.db.Column;
import framework.encoder.PkgStyle;
import framework.template.Template;
import template.printer.ZjDaoPrinter;
import template.printer.ZjPojoPrinter;

public class ZjTemplate extends Template{

	
	public static String BASE_PACKAGE ;
	public static String POJO_PACKAGE = BASE_PACKAGE+".vo";
	public static String DAO_PACKAGE = BASE_PACKAGE+".dao";
	public static String SERVICE_PACKAGE = BASE_PACKAGE+".service";
	
	public ZjTemplate(String pkg, String className, Column col, String tableName) {
		super(pkg, className, col, tableName);
		BASE_PACKAGE = pkg;
		String pojoPkg = this.pkg + PkgStyle.POJO_PACKAGE;
		String daoPkg = this.pkg + PkgStyle.DAO_PACKAGE;
//		String servicePkg = this.pkg + PkgStyle.SERVICE_PACKAGE;
		pojoPrinter = new ZjPojoPrinter(pojoPkg, this.className, tableName,col);
		daoPrinter = new ZjDaoPrinter(daoPkg, this.className, tableName,col);
//		servicePrinter = new ServicePrinter(servicePkg, this.className, tableName,col);
	}

	public void print() throws Exception {
		pojoPrinter.print();
		daoPrinter.print();
//		servicePrinter.print();
	}
}
