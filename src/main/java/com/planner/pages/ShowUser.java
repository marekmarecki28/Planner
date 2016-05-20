package com.planner.pages;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.planner.dao.UserDAO;
import com.planner.entities.User;

public class ShowUser {
	
	@Property
	private User user;
	
	@Inject
	private UserDAO userDAO;
	
	public String getUserName()
	{
		Subject currentUser = SecurityUtils.getSubject();
		return (String) currentUser.getPrincipal().toString();
	}
	
	void onActivate()
	{
		user = userDAO.getUserByEmail(getUserName());
	}

}
