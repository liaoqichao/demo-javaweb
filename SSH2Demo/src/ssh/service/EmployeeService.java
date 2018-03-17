package ssh.service;

import java.util.List;

import ssh.domain.Employee;

public interface EmployeeService {

	void save(Employee employee);

	void delete(Integer id);

	void update(Employee employee);

	Employee getEmployee(Integer id);

	List<Employee> getEmployeeList();

}