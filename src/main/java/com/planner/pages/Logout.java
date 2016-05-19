package com.planner.pages;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

public class Logout {

	  @Inject
	  private Logger logger;

	  @Inject
	  private AlertManager alertManager;
	  
	  public void onActivate()
	  {
		  setLogOut();
	  }

	  public void setLogOut()
	  {
		  Subject currentUser = SecurityUtils.getSubject();
		  currentUser.logout();
	  }
}
