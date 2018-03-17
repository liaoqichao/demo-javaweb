package template.printer;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import framework.db.ColAttribute;
import framework.db.Column;
import framework.printer.ImportPrinter;
import framework.printer.PackagePrinter;
import framework.printer.PojoPrinter;
import framework.printer.Printer;
import framework.type.JavaSimpleType;
import framework.util.WritelnWriter;

public class ZjPojoPrinter extends PojoPrinter {

	public ZjPojoPrinter(String pkg, String className, String tableName, Column col) {
		super(pkg, className, tableName, col);
	}

	public void print() throws Exception {
		String path = this.pkg.replaceAll("\\.", "/");
		File file = createFile(path,this.className,Printer.POJO);
		try(FileWriter fw = new FileWriter(file);
				WritelnWriter ww = new WritelnWriter(fw);){
			PackagePrinter pp = new PackagePrinter(ww);
			pp.print(pkg);
			ImportPrinter ip = new ImportPrinter(ww);
			Set<String> importSet = getImportSet(col,tableName);
			ip.print(importSet);
			ww.writeln("public class "+this.className+"{");
			ww.newLine();
			PojoMemberPrinter mp = new PojoMemberPrinter(ww);
			mp.print(col,tableName);
			ww.newLine();
			PojoMethodPrinter methodPrinter = new PojoMethodPrinter(ww);
			methodPrinter.printMethodList(pkg,className,tableName,col);
			ww.writeln("}");
		}
	}

	private Set<String> getImportSet(Column col, String tableName) throws Exception {
		Set<String> importSet = new HashSet<String>();
		addImport(importSet,col.getKeyMap());
		addImport(importSet,col.getColMap());
		return importSet;
	}
	
	private void addImport(Set<String> importSet, Map<String,ColAttribute> map) throws Exception{
		for (Map.Entry<String, ColAttribute> entry : map.entrySet()) {
			if(!entry.getValue().getJavaType().contains("java.lang.")  // 不需要导包
					&& !importSet.contains(entry.getValue().getJavaType()) // 重复导包
					&& !JavaSimpleType.getSimpleTypeSet().contains(entry.getValue().getJavaType())){ // 简单类型
				importSet.add(entry.getValue().getJavaType());
			}
		}
	}
}
