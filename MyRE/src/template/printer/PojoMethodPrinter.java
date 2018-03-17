package template.printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.db.ColAttribute;
import framework.db.Column;
import framework.encoder.Privilege;
import framework.printer.Method;
import framework.printer.MethodPrinter;
import framework.util.WritelnWriter;

public class PojoMethodPrinter extends MethodPrinter {

	public PojoMethodPrinter(WritelnWriter ww) {
		super(ww);
	}

	
	@Override
	public List<Method> getMethodList(String pkg, String className, String tableName, Column col) throws Exception {
		List<Method> methods = new ArrayList<Method>();
		methods.addAll(getMethodList(col.getKeyMap()));
		methods.addAll(getMethodList(col.getColMap()));
		return methods;
	}
	
	private List<Method> getMethodList(Map<String, ColAttribute> map) throws Exception{
		List<Method> methods = new ArrayList<Method>();
		for (Map.Entry<String, ColAttribute> entry : map.entrySet()) {
			String memberName = entry.getKey().substring(0,1).toUpperCase() + entry.getKey().substring(1);
			Method getter = new Method();
			getter.setJavaDoc(entry.getValue().getRemarks());
			getter.setPrivilege(Privilege.PUBLIC);
			getter.setReturnType(entry.getValue().getJavaType().substring(entry.getValue().getJavaType().lastIndexOf(".")+1));
			getter.setMethodName("get"+memberName);
			if("java.lang.String".equals(entry.getValue().getJavaType())){
				getter.setReturnVariable("this."+entry.getKey()+" == null ? \"\" :"+" this."+entry.getKey()+".trim()");
			} else if("java.math.BigDecimal".equals(entry.getValue().getJavaType())){
				getter.setReturnVariable("this."+entry.getKey()+" == null ? new BigDecimal(\"0\") :"+" this."+entry.getKey());
			} else{
				getter.setReturnVariable("this."+entry.getKey());
			}
			methods.add(getter);
			
			Method setter = new Method();
			setter.setPrivilege(Privilege.PUBLIC);
			setter.setReturnType("void");
			setter.setMethodName("set"+memberName);
			Map<String, String> varAndTypeMap = new HashMap<String, String>();
			varAndTypeMap.put(entry.getKey(), entry.getValue().getJavaType().substring(entry.getValue().getJavaType().lastIndexOf(".")+1));
			setter.setVarAndTypeMap(varAndTypeMap);
			setter.setBody("this."+entry.getKey() +" = " + entry.getKey() +";");
			methods.add(setter);
		}
		return methods;
		
	}

}
