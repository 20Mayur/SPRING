package com.Springboot.MySql.DFP;

import java.util.List;


public interface DesignF {
	
    List<User> getuser();

	
	String addUser(User user);
	
	String upadateUser(User user);
	
	String findbyId(Integer Id,User user);

	String deleteid(Integer Id,User user);
}

