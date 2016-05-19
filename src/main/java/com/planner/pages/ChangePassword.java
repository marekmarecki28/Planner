package com.planner.pages;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.planner.auth.MySaltedAuthenticationInfo;
import com.planner.dao.UserDAO;
import com.planner.entities.User;

public class ChangePassword {
	
	@Property
	private String oldPassword;
	
	@Property
	private String newPassword;
	
	@Property
	private String newPassword1;
	
	@InjectComponent
	private Form changePassword;
	
	@Inject
	private UserDAO userDAO;
	
	void onValidateFromChangePassword()
	{
		System.out.println("oldPassword= " + oldPassword);
		System.out.println("oldPassword= " + newPassword);
		System.out.println("oldPassword= " + newPassword1);

		
		User user = userDAO.getUserByEmail(this.getUserName());
		System.out.println("USER->>>> " + user.getEmail());
		
		if(user != null)
		{
			SaltedAuthenticationInfo info = new MySaltedAuthenticationInfo(
			this.getUserName(), user.getPassword(), user.getSalt());
			
			System.out.println("SALTED AUTH: " + info.getCredentials().toString());
		}
		
	}
	
	public String getUserName()
	  {
		  Subject currentUser = SecurityUtils.getSubject();
		  return (String) currentUser.getPrincipal().toString();
	  }

}
