package com.planner.pages;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;
import org.tynamo.security.services.SecurityService;

public class Login
{
   @Inject
	private SecurityService securityService;
    
    @Inject
	private AlertManager alertManager;
    
    @Persist
    private String userName;
    
    @Persist
    private String userPassword;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	void onActivate(){
    	Subject currentUser = securityService.getSubject();

		if (currentUser == null)
		{
			throw new IllegalStateException("Subject can`t be null");
		}

		UsernamePasswordToken token = new UsernamePasswordToken(this.userName, this.userPassword);

		try
		{
			currentUser.login(token);
		} catch (UnknownAccountException e)
		{
			alertManager.error("Account not exists");
			
		} catch (IncorrectCredentialsException e)
		{
			alertManager.error("Wrong password");

		} catch (LockedAccountException e)
		{
			alertManager.error("Account locked");

		} catch (AuthenticationException e)
		{
			alertManager.error("Authentication Error");

		}

 }

}
