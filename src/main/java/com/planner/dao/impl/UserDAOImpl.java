package com.planner.dao.impl;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.planner.dao.UserDAO;
import com.planner.entities.Employee;
import com.planner.entities.User;

public class UserDAOImpl implements UserDAO{
	
	private final Session session;

	public UserDAOImpl(Session s) {
		session = s;
	}
	
	public User getUserByEmail(String email) {

		User user = (User) session.createQuery("from User where email=?").setString(0, email).uniqueResult();
		return user;
	}

	public Long getUserId(String email) {
		User user = (User) session.createQuery("from User where email=?").setString(0, email).uniqueResult();
		return user.getUserId();
	}
	
	@Override
	public void updateUser(User user) {
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 session.update(user); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}
}
