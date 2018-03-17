package ssh.dao;

import java.util.List;

import ssh.domain.Employee;

public interface EmployeeDao {

	/**
	 * ��
	 * @param employee
	 */
	void save(Employee employee);

	/**
	 * ɾ����������
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * ��
	 * @param employee
	 */
	void update(Employee employee);

	/**
	 * ����������ѯ
	 * @param id
	 * @return
	 */
	Employee getEmployee(Integer id);

	/**
	 * ��ѯ����
	 * @return
	 */
	List<Employee> getEmployeeList();

}