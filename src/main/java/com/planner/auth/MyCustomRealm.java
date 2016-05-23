package com.planner.auth;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import com.planner.dao.UserDAO;
import com.planner.dao.impl.UserDAOImpl;
import com.planner.entities.User;
import com.planner.util.HibernateUtil;

public class MyCustomRealm extends JdbcRealm{
	
	@Inject
	private HashedCredentialsMatcher hm;
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// identify account to log to
		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		final String username = userPassToken.getUsername();

		if (username == null) {
			System.out.println("Username is null.");
			return null;
		}

		// read password hash and salt from db
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			UserDAO userDAO = new UserDAOImpl(session);
			final User user = userDAO.getUserByEmail(username);

			if (user == null) {
				System.out.println("No account found for user [" + username + "]");
				return null;
			}
			
			final Long userId = user.getUserId();
			System.out.println("userId= " + userId);
            
			SaltedAuthenticationInfo info = new MySaltedAuthenticationInfo(
					username, user.getPassword(), user.getSalt());
			
			return info;
		}
		catch(Exception e)
		{
			System.out.println("Exceptption=  " + e);
			throw new AuthenticationException();
		} 
		finally {
			session.getTransaction().commit();
			if (session.isOpen()) session.close();
		}

	}

}
