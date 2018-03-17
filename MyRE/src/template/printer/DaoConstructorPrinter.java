package template.printer;

import framework.printer.ConstructorPrinter;
import framework.util.WritelnWriter;

public class DaoConstructorPrinter extends ConstructorPrinter {

	public DaoConstructorPrinter(WritelnWriter ww) {
		super(ww);
	}

	public void print(String className) throws Exception {
		ww.writetabln("public "+className+"(Connection connection){");
		ww.writetabln("super(connection);",2);
		ww.writetabln("}");
		ww.newLine();
	}

}
