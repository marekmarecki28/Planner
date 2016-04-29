package com.planner.encoders;

import org.apache.tapestry5.ValueEncoder;

import com.planner.dao.EmployeeDAO;
import com.planner.entities.Employee;

public class EmployeeEncoder implements ValueEncoder<Employee> {
	
	private EmployeeDAO employeeDAO;
	
	public EmployeeEncoder(EmployeeDAO employeeDAO)
	{
		this.employeeDAO = employeeDAO;
	}

	@Override
	public String toClient(Employee value) {
		return String.valueOf(value.getEmployeeId());
	}

	@Override
	public Employee toValue(String id) {
		return employeeDAO.getEmployee(Long.parseLong(id));
	}

}
