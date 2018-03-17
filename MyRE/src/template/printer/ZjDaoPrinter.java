package template.printer;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import framework.db.ColAttribute;
import framework.db.Column;
import framework.encoder.PkgStyle;
import framework.printer.DaoPrinter;
import framework.printer.ImportPrinter;
import framework.printer.PackagePrinter;
import framework.printer.Printer;
import framework.type.JavaSimpleTypeMapping;
import framework.util.WritelnWriter;

public class ZjDaoPrinter extends DaoPrinter{

	public ZjDaoPrinter(String pkg, String className, String tableName, Column col) {
		super(pkg, className, tableName, col);
	}


	private Set<String> getImportSet() throws Exception {
		Set<String> importSet = new HashSet<String>();
		importSet.add("java.sql.Connection");
		importSet.add("java.sql.ResultSet");
		importSet.add("com.zjft.common.dao.CommonDao");
		importSet.add(this.pkg.substring(0, this.pkg.lastIndexOf(PkgStyle.DAO_PACKAGE))+PkgStyle.POJO_PACKAGE+"."+this.className);
		return importSet;
	}


	public void print() throws Exception {
		String path = this.pkg.replaceAll("\\.", "/");
		File file = createFile(path,this.className,Printer.DAO);
		try(FileWriter fw = new FileWriter(file);
				WritelnWriter ww = new WritelnWriter(fw);){
			PackagePrinter pp = new PackagePrinter(ww);
			pp.print(this.pkg);
			ImportPrinter ip = new ImportPrinter(ww);
			Set<String> importSet = getImportSet();
			ip.print(importSet);
			
			String idType = "TypeOfId";
			if(col.getKeyMap().size() == 1){
				for (Map.Entry<String, ColAttribute> entry : col.getKeyMap().entrySet()) {
					String className = entry.getValue().getJavaType();
					if(!className.contains("java.lang")){
						className = JavaSimpleTypeMapping.getType(className); // 简单类型改为包装类型
					}
					idType = Class.forName(className).getSimpleName();
				}
			}
			ww.writeln("public class "+this.className+Printer.DAO+" extends CommonDao<"+this.className+","+idType+">"+" {");

			
			DaoConstructorPrinter dcp = new DaoConstructorPrinter(ww);
			dcp.print(this.className+Printer.DAO);
			DaoMethodPrinter dmp = new DaoMethodPrinter(ww);
			dmp.printMethodList(this.pkg,this.className,this.tableName,this.col);
			ww.writeln("}");
		}
	}

}
