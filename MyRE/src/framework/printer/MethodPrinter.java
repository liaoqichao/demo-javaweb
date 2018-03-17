package framework.printer;

import java.util.List;
import java.util.Map;

import framework.config.Config;
import framework.db.Column;
import framework.type.DataBaseTypeMapping;
import framework.util.WritelnWriter;

public abstract class MethodPrinter {

	protected WritelnWriter ww;
	protected DataBaseTypeMapping dbtp;
	
	public MethodPrinter(WritelnWriter ww){
		try {
			this.ww = ww;
			dbtp = Config.getTypeMapping();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	abstract public List<Method> getMethodList(String pkg, String className, String tableName, Column col) throws Exception;
	
	public void printMethodList(String pkg, String className, String tableName, Column col) throws Exception {
		List<Method> methodList = getMethodList(pkg, className, tableName, col);
		for (Method method : methodList) {
			this.printMethod(method);
		}
	}
	
	
	protected void printMethod(Method method) throws Exception{
		printJavaDoc(method.getJavaDoc());
		printPrivilege(method.getPrivilege());
		printReturnType(method.getReturnType());
		printMethodName(method.getMethodName());
		printPram(method.getVarAndTypeMap());
		printThrowsException(method.getExceptions());
		ww.write("{");
		ww.newLine();
		printBody(method.getBody());
		printReturn(method.getReturnVariable());
		ww.writetabln("}");
		ww.newLine();
	}
	
	protected void printPrivilege(String privilege) throws Exception {
		ww.writetab(privilege, 1);
		ww.write(" ");
	}

	protected void printReturnType(String returnType) throws Exception {
		ww.write(returnType);
		ww.write(" ");
	}

	protected void printMethodName(String methodName) throws Exception {
		ww.write(methodName);
	}

	protected void printPram(Map<String, String> varAndTypeMap) throws Exception {
		ww.write("(");
		if(varAndTypeMap!= null && varAndTypeMap.size()>0){
			StringBuilder param = new StringBuilder();
			for (Map.Entry<String, String> entry : varAndTypeMap.entrySet()) {
				if(param.length()>0){
					param.append(", ");
				}
				param.append(entry.getValue()+" "+entry.getKey());
			}
			ww.write(param.toString());
		}
		ww.write(")");
		ww.write(" ");
	}

	protected void printThrowsException(String[] exceptions) throws Exception {
		if(exceptions != null && exceptions.length>0){
			StringBuilder sb = new StringBuilder();
			sb.append("throws ");
			for (String exception : exceptions) {
				if(sb.length() > 7){
					sb.append(", ");
				}
				sb.append(exception);
			}
			ww.write(sb.toString());
		}
	}
	
	protected void printBody(String body) throws Exception{
		if(body != null && body.length()>0){
			ww.writetabln(body.toString(), 2);
		}
	}

	protected void printReturn(String returnVariable) throws Exception {
		if(returnVariable != null && returnVariable.length() > 0){
			ww.writetabln("return "+returnVariable+";", 2);
		}
	}
	
	protected void printJavaDoc(String javaDoc) throws Exception{
		if(javaDoc != null && javaDoc.length()>0){
			// ½â¾ö»»ÐÐ
//			javaDoc = javaDoc.replaceAll(System.getProperty("line.seperator"), System.getProperty("line.seperator")+"\t"+" * ");
			ww.writetabln("/**");
			ww.writetabln(" * "+javaDoc);
			ww.writetabln(" */");
		}
	}
}
