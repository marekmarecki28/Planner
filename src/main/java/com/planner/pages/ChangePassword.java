package com.planner.pages;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
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
	
	@InjectComponent("oldPassword")
    private PasswordField oldPasswordField;
	
	@InjectComponent("newPassword1")
    private PasswordField newPassword1Field;
	
	void onValidateFromChangePassword()
	{
		System.out.println("oldPassword= " + oldPassword);
		System.out.println("oldPassword= " + newPassword);
		System.out.println("oldPassword= " + newPassword1);

		
		User user = userDAO.getUserByEmail(this.getUserName());
		
		if(user != null && oldPassword != null)
		{
			AuthenticationToken at = new UsernamePasswordToken(user.getEmail(),oldPassword);
			SaltedAuthenticationInfo si = new MySaltedAuthenticationInfo(user.getEmail(),user.getPassword(),user.getSalt());
			HashedCredentialsMatcher authenticator = new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME);
			authenticator.setHashIterations(1024);
			authenticator.setStoredCredentialsHexEncoded(false);
			
			boolean successfulAuthentication = authenticator.doCredentialsMatch(at, si);
			System.out.println("WYNIK POROWNANIA: " + successfulAuthentication);
			if(!successfulAuthentication)
			{
				changePassword.recordError(oldPasswordField,"Bledne haslo");
			}
			else 
			{
				if(newPassword.equals(newPassword1))
				{
					generatePassword(user, newPassword);
					userDAO.updateUser(user);
				}
				else
				{
					changePassword.recordError(newPassword1Field,"Podane haslo nie zgadza sie");
				}
			}
		}
		
	}
	
	public String getUserName()
	  {
		  Subject currentUser = SecurityUtils.getSubject();
		  return (String) currentUser.getPrincipal().toString();
	  }
	
	private void generatePassword(User user, String plainTextPassword) 
	{
	  RandomNumberGenerator rng = new SecureRandomNumberGenerator();
	  Object salt = rng.nextBytes();

	  String hashedPasswordBase64 = new Sha256Hash(plainTextPassword, salt,1024).toBase64();
	 
	  user.setPassword(hashedPasswordBase64);
	  user.setSalt(salt.toString());
	}
	
	Object onSuccess()
	{
		return Index.class;
	}
	

}
