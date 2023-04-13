package com.Springboot.MySql.DFP;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class SqlCrud implements DesignF {
	@Autowired
	private SqlRepo sqlRepo;
	
	Logger logger=LoggerFactory.getLogger(SqlCrud.class);

	@Override
	public List<User> getuser() {
		logger.info("getting all user from mysql");
	List<User> all=sqlRepo.findAll();
		return all;
	}

	@Override
	public String addUser(User user) {
		logger.info("getting added user from mysql");
		sqlRepo.save(user);
		return "Added Successfully";
	}

	@Override
	public String upadateUser(User user) {
		// TODO Auto-generated method stub
		logger.info("getting updated user from mysql");
		sqlRepo.save(user);
		return "updated successfully";
	}
	
	@Override
	public String findbyId(@PathVariable Integer Id,User user) {
		// TODO Auto-generated method stub
		logger.info("getting FindbyId user from mysql");
		sqlRepo.findById(Id);
		return "done finding";
	}

	@Override
	public String deleteid(Integer Id, User user) {
		// TODO Auto-generated method 
		logger.info("getting deletedId user from mysql");
       sqlRepo.deleteById(Id);
		return "deleted Successfully";
	}

	
}
