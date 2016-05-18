package com.planner.dao;

import com.planner.entities.User;

public interface UserDAO {

	User getUserByEmail(String username);
	Long getUserId(String email);

}
