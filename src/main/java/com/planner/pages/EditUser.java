package com.planner.pages;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.planner.dao.UserDAO;
import com.planner.entities.User;

public class EditUser {
	
	@Property
	private User user;
	
	@Inject
	private UserDAO userDAO;
	
	@InjectComponent
	private BeanEditForm editForm;
	
	public String getUserName()
	{
		Subject currentUser = SecurityUtils.getSubject();
		return (String) currentUser.getPrincipal().toString();
	}
	
	void onActivate()
	{
		user = userDAO.getUserByEmail(getUserName());
	}
	
	void onValidateFromEditForm()
	{
		System.out.println("onValidateFromEditForm");
		System.out.println("firstName " + user.getFirstName());
		System.out.println("lastName " + user.getLastName());
		userDAO.updateUser(user);
	}
	
	Object onSuccess()
	{
		return ShowUser.class;
	}
}
