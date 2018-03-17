package framework.printer;

import java.util.Map;

public class Method {

	private String javaDoc;
	private String privilege;
	private String returnType;
	private String methodName;
	private Map<String, String> varAndTypeMap; 
	private String[] exceptions;
	private String body;
	private String returnVariable;
	
	
	public Method() {
		super();
	}
	
	public Method(String privilege, String returnType, String methodName) {
		super();
		this.privilege = privilege;
		this.returnType = returnType;
		this.methodName = methodName;
	}



	public String getJavaDoc() {
		return javaDoc;
	}
	public void setJavaDoc(String javaDoc) {
		this.javaDoc = javaDoc;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Map<String, String> getVarAndTypeMap() {
		return varAndTypeMap;
	}
	public void setVarAndTypeMap(Map<String, String> varAndTypeMap) {
		this.varAndTypeMap = varAndTypeMap;
	}
	public String[] getExceptions() {
		return exceptions;
	}
	public void setExceptions(String[] exceptions) {
		this.exceptions = exceptions;
	}
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getReturnVariable() {
		return returnVariable;
	}
	public void setReturnVariable(String returnVariable) {
		this.returnVariable = returnVariable;
	}
	
}
