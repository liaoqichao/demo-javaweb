package ssh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ssh.dao.EmployeeDao;
import ssh.domain.Employee;
import ssh.service.EmployeeService;

//@Service("employeeService")
public class EmployeeServiceBean implements EmployeeService {

	@Resource
	private EmployeeDao employeeDao;
	
	/* (non-Javadoc)
	 * @see ssh.service.impl.EmployeeService#save(ssh.domain.Employee)
	 */
	@Transactional
	public void save(Employee employee){
		employeeDao.save(employee);
	}
	
	/* (non-Javadoc)
	 * @see ssh.service.impl.EmployeeService#delete(java.lang.Integer)
	 */
	@Transactional
	public void delete(Integer id){
		employeeDao.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see ssh.service.impl.EmployeeService#update(ssh.domain.Employee)
	 */
	@Transactional
	public void update(Employee employee){
		employeeDao.update(employee);
	}
	/* (non-Javadoc)
	 * @see ssh.service.impl.EmployeeService#getEmployee(java.lang.Integer)
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Employee getEmployee(Integer id){
		return employeeDao.getEmployee(id);
	}
	
	/* (non-Javadoc)
	 * @see ssh.service.impl.EmployeeService#getEmployeeList()
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Employee> getEmployeeList(){
		return employeeDao.getEmployeeList();
	}
}
