package com.planner.dao;

import java.util.List;

import com.planner.entities.Employee;

public interface EmployeeDAO {
	public List<String> getEmployeeNames();
	public List<Employee> getEmployees();
	public Employee getEmployee(Long id);
	public List<Employee> getPokojowa();
	public List<Employee> getLobby();
	public List<Employee> getSpa();
}
