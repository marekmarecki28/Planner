package com.planner.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;

import com.planner.dao.UserDAO;
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
}
