package com.planner.pages;


import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.tynamo.security.services.SecurityService;

import com.planner.dao.UserDAO;
import com.planner.entities.User;


public class NewAccount {
	
	@Property
	@Persist
	private User user;
	
	@Inject
    private Session session;
 
    @InjectPage
    private Login login;
    
    @Inject
    private UserDAO userDAO;
    
    @Inject
	private SecurityService securityService;
    
    @Inject
	private AlertManager alertManager;
    
    @InjectComponent
    private BeanEditForm myBeanEditor;
    
    @InjectComponent("email")
    private TextField emailField;

	@CommitAfter
    Object onSuccess()
    {    	
    	registrate(user.getEmail(),user.getPassword());
    	System.out.println("After registrate");
    	
        return login;
    }
    
    void onPrepareFromMyBeanEditor() {
        user = new User();
      }
    
    void onValidateFromMyBeanEditor()
    {
    	System.out.println("onValidateFromMyBeanEditior");
    	System.out.println("User: " + user.getFirstName());
    	
    	if(user.getEmail() != null)
    	{
    		User checkUser = userDAO.getUserByEmail(user.getEmail());
    		if(checkUser != null){
    			myBeanEditor.recordError(emailField,"Ten email istnieje już w naszej bazie.");
    		}
    	}
    }
    
    public void registrate(String email, String plainTextPassword) {
    	  
    	  user.setUsername(email);
    	 
    	  generatePassword(user, plainTextPassword);
    	  
    	  login.setUserName(email);
    	  login.setUserPassword(plainTextPassword);
    	  
    	  session.persist(user);

    	  System.err.println("User with email:" + user.getEmail() + " hashedPassword:"+ user.getPassword() + " salt:" + user.getSalt());
    	}
    	 
	private void generatePassword(User user, String plainTextPassword) {
	  RandomNumberGenerator rng = new SecureRandomNumberGenerator();
	  Object salt = rng.nextBytes();

	  String hashedPasswordBase64 = new Sha256Hash(plainTextPassword, salt,1024).toBase64();
	 
	  user.setPassword(hashedPasswordBase64);
	  user.setSalt(salt.toString());
	}

}
