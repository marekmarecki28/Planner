package com.planner.dao;

import java.util.List;

import com.planner.entities.Employee;

public interface EmployeeDAO {
	public List<String> getEmployeeNames();
	public List<Employee> getEmployees(Long hotelId);
	public Employee getEmployee(Long id);
	public List<Employee> getPokojowa(Long hotelId);
	public List<Employee> getLobby(Long hotelId);
	public List<Employee> getSpa(Long hotelId);
	public void createEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployee(Long id);
}
