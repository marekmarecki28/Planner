package com.planner.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.planner.dao.EmployeeDAO;
import com.planner.entities.Employee;

public class Positions {
	
	@Inject
	private EmployeeDAO employeeDAO;
	
	@Property
	private Employee employee;

	public List<Employee> getPokojowa()
	{
		return employeeDAO.getPokojowa();
	}
	
	public List<Employee> getLobby()
	{
		return employeeDAO.getLobby();
	}
	
	public List<Employee> getSpa()
	{
		return employeeDAO.getSpa();
	}
	
	
}
