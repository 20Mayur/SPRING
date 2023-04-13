package com.Springboot.MySql.DFP;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoCrud implements DesignF {
	@Autowired
	private MongoRepo mongoRepo;
	
	Logger logger=LoggerFactory.getLogger(MongoCrud.class);

	@Override
	public List<User> getuser() {
		// TODO Auto-generated method stub
		logger.info("getting all user from mongo");
		List<User> all=mongoRepo.findAll();
		return all;
	}

	@Override
	public String addUser(User user) {
		// TODO Auto-generated method stub
		logger.info("getting added user from mongo");
		mongoRepo.save(user);
		return "Added Successfully";
	}

	@Override
	public String upadateUser(User user) {
		// TODO Auto-generated method stub
		logger.info("getting updated user from mongo");
		mongoRepo.save(user);
		return "updated Successfully";
	}

	@Override
	public String findbyId(Integer Id, User user) {
		// TODO Auto-generated method stub
		logger.info("getting FindbyId user from mongo");
		mongoRepo.findById(Id);
		return "done";
	}

	@Override
	public String deleteid(Integer Id, User user) {
		// TODO Auto-generated method stub
		logger.info("getting Deletedid user from mongo");
		mongoRepo.deleteById(Id);
		return "deleted Successfully";
	}

}
