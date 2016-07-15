package com.planner.entities;

import javax.persistence.Entity;

public class EmployeePositions {
	
	public EmployeePositions(){}
	
	public EmployeePositions(Long employeeId, String firstName, String lastName, String description, String positionDescr) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.positionDescr = positionDescr;
	}
	
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String description;
	private String positionDescr;
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPositionDescr() {
		return positionDescr;
	}
	public void setPositionId(String positionDescr) {
		this.positionDescr = positionDescr;
	}

}
