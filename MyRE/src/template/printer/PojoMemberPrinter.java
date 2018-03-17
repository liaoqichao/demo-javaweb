package template.printer;

import java.util.Map;

import framework.db.ColAttribute;
import framework.db.Column;
import framework.printer.MemberPrinter;
import framework.util.WritelnWriter;

public class PojoMemberPrinter extends MemberPrinter {

	public PojoMemberPrinter(WritelnWriter ww) {
		super(ww);
	}

	public void print(Column col,String tableName) throws Exception {
		printCol(col.getKeyMap());
		printCol(col.getColMap());
	}
	
	private void printCol(Map<String,ColAttribute> map) throws Exception{
		for (Map.Entry<String,ColAttribute> entry : map.entrySet()) {
			ww.writeln("\t"+"private "+entry.getValue().getJavaType().substring(entry.getValue().getJavaType().lastIndexOf(".")+1)+" "+entry.getKey()+";"+"\t//"+entry.getValue().getRemarks());
		}
	}

}
