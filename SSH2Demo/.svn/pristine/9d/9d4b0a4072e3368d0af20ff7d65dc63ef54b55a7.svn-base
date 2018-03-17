package ssh.web;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;

import ssh.domain.Employee;
import ssh.service.EmployeeService;

public class EmployeeAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private EmployeeService employeeService;
	
	private List<Employee> employeeList;
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	private Employee employee;
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	/**
	 * 添加员工
	 * @return
	 */
	public String add(){
		employeeService.save(employee);
		this.msg = "添加成功";
		System.out.println("添加成功");
		return "msg";
	}

	
	public String getOne(){
		Employee emp = employeeService.getEmployee(employee.getId());
		this.employee = emp;
		return "show";
	}
	
	public String getList(){
		this.employeeList = employeeService.getEmployeeList();
		return "show";
	}
	
	public String update(){
		Employee emp = employeeService.getEmployee(employee.getId());
		if(emp != null){
			emp.setName(employee.getName());
			employeeService.update(emp);
			this.msg="修改成功";
		} else{
			this.msg = "ID不存在";
		}
		return "msg";
	}
	
	public String delete(){
		Employee emp = employeeService.getEmployee(employee.getId());
		if(emp != null){
			employeeService.delete(emp.getId());
			this.msg="删除成功";
		} else{
			this.msg = "ID不存在";
		}
		return "msg";
	}
}
