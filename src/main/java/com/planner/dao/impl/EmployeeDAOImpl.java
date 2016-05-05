package com.planner.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.planner.dao.EmployeeDAO;
import com.planner.entities.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	private final Session session;

	public EmployeeDAOImpl(Session s) {
		session = s;
	}
	@Override
	public List<String> getEmployeeNames() {
		List<String> employeeNames = session.createCriteria(Employee.class).setProjection(Projections.property("firstName")).list();
		return employeeNames;
	}
	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = session.createCriteria(Employee.class).list();
		return employees;
	}
	@Override
	public Employee getEmployee(Long id) {
		Employee employee = (Employee) session.createQuery("from Employee where employee_id = " + id).uniqueResult();
		System.out.println("Employee found= " + employee);
		return employee;
	}
	@Override
	public List<Employee> getPokojowa() {
		List<Employee> employees = session.createQuery("from Employee where position_id = 0").list();
		return employees;
	}
	@Override
	public List<Employee> getLobby() {
		List<Employee> employees = session.createQuery("from Employee where position_id = 1").list();
		return employees;
	}
	@Override
	public List<Employee> getSpa() {
		List<Employee> employees = session.createQuery("from Employee where position_id = 2").list();
		return employees;
	}

}
