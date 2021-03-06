package com.planner.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.planner.dao.EmployeeDAO;
import com.planner.entities.Employee;
import com.planner.entities.EmployeePositions;
import com.planner.entities.Position;

public class EmployeeDAOImpl implements EmployeeDAO {

	private final Session session;
	private Employee employee;

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
		List<Employee> employees = session.createQuery("from Employee where position_id = 1 and hotel_id = " + hotelId).list();
		return employees;
	}
	@Override
	public List<Employee> getLobby(Long hotelId) {
		List<Employee> employees = session.createQuery("from Employee where position_id = 2 and hotel_id = " + hotelId).list();
		return employees;
	}
	@Override
	public List<Employee> getSpa(Long hotelId) {
		List<Employee> employees = session.createQuery("from Employee where position_id = 3 and hotel_id = " + hotelId).list();
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
	
	@Override
	public void updateEmployee(Employee employee) {
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 session.update(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}
	@Override
	public void deleteEmployee(Long id) {
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 employee = (Employee) session.load(Employee.class,id);
			 session.delete(employee);
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}
	
	public List<EmployeePositions> getEmployeePositions(Long positionId, Long hotelId)
	{
		List<EmployeePositions> listEmployeesPositions = new ArrayList<EmployeePositions>();
		List<Employee> employees = session.createQuery("from Employee where position_id = " + positionId + " and hotel_id = " + hotelId).list();
		
		for(Employee employee :employees)
		{
			Position position = (Position) session.createQuery("from Position where position_id = " + positionId).uniqueResult();
			EmployeePositions employeePositions = new EmployeePositions(employee.getEmployeeId(),employee.getFirstName(),employee.getLastName(),employee.getDescription(),position.getDescription());
			listEmployeesPositions.add(employeePositions);
		}
		return listEmployeesPositions;
	}

}
