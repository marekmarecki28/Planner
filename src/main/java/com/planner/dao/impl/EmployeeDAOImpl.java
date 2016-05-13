package com.planner.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
	public List<Employee> getEmployees(Long hotelId) {
		List<Employee> employees = session.createQuery("from Employee where hotel_id = " + hotelId).list();
		return employees;
	}
	@Override
	public Employee getEmployee(Long id) {
		Employee employee = (Employee) session.createQuery("from Employee where employee_id = " + id).uniqueResult();
		System.out.println("Employee found= " + employee);
		return employee;
	}
	@Override
	public List<Employee> getPokojowa(Long hotelId) {
		List<Employee> employees = session.createQuery("from Employee where position_id = 0 and hotel_id = " + hotelId).list();
		return employees;
	}
	@Override
	public List<Employee> getLobby(Long hotelId) {
		List<Employee> employees = session.createQuery("from Employee where position_id = 1 and hotel_id = " + hotelId).list();
		return employees;
	}
	@Override
	public List<Employee> getSpa(Long hotelId) {
		List<Employee> employees = session.createQuery("from Employee where position_id = 2 and hotel_id = " + hotelId).list();
		return employees;
	}
	@Override
	public void createEmployee(Employee employee) {
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 session.save(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}

}
