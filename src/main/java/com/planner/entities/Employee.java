package com.planner.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table( name = "EMPLOYEE" )
public class Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1066921437955025258L;
	
	public Employee(){
	}



	public Employee(Long employee_id, String firstName, String lastName, Long age, String sex, String email,
			String description) {
		this.employeeId = employee_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.sex = sex;
		this.email = email;
		this.description = description;
		this.fullName = firstName + " " +lastName;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	@Column(name = "EMPLOYEE_ID")
	private Long employeeId;
	
	private String firstName;
	private String lastName;
	private Long age;
	private String sex;
	private String email;
	private String description;
	private String fullName;
	
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employee_id) {
		this.employeeId = employee_id;
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
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		fullName = firstName + " " + lastName;
		return fullName;
	}

	
	@Override
	public String toString() {
        final String DIVIDER = ", ";
        
        StringBuilder buf = new StringBuilder();
        buf.append(this.getClass().getSimpleName() + ": ");
        buf.append("[");
        buf.append("id=" + employeeId + DIVIDER);
        buf.append("firstName=" + firstName + DIVIDER);
        buf.append("lastName=" + lastName);
        buf.append("]");
        return buf.toString();
    }
}
