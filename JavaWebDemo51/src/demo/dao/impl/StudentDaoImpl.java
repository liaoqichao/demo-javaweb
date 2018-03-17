package demo.dao.impl;

import demo.dao.StudentDao;
import demo.domain.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public void add(Student stu) {
		System.out.println("studentDaoImpl#add");
	}

	@Override
	public void update(Student stu) {
		System.out.println("studentDaoImpl#update");
	}

}
