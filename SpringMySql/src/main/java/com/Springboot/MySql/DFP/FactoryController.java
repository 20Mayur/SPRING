package com.Springboot.MySql.DFP;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FactoryController {
	@Autowired
	private FactorySelection factorySelection;
	
	Logger logger=LoggerFactory.getLogger(FactoryController.class);

	@PostMapping("/getAll/{DBtype}")
	public List<User> getuser(@RequestBody User user,@PathVariable String DBtype){
		logger.info("Inside getUser Method");
		logger.info("received getUser request with user:" +user.toString()+"and DBType:"+DBtype);
		DesignF df = factorySelection.getDataBase(DBtype);
		return df.getuser();
	}
	@PostMapping("/add/{DBtype}")
	public String addUser(@RequestBody User user,@PathVariable String DBtype) {
		logger.info("Inside addUser Method");
		 logger.info("Received addUser request with user: " + user.toString() + " and DBtype: " + DBtype);
		DesignF df=factorySelection.getDataBase(DBtype);
		return df.addUser(user);
	}
	@PatchMapping("/update/{DBtype}")
	public String updateUser(@RequestBody User user,@PathVariable String DBtype) {
		logger.info("inside updateUser method");
		 logger.info("Received updateUser request with user: " + user.toString() + " and DBtype: " + DBtype);
		DesignF df=factorySelection.getDataBase(DBtype);
		return df.upadateUser(user);
	}
	@GetMapping("/find/{Id}/{DBtype}")
	public String FindById(@RequestBody User user,@PathVariable String DBtype,@PathVariable Integer Id) {
		logger.info("inside FindById method");
		 logger.info("Received FindById request with user: " + user.toString() + " and DBtype: " + DBtype);
		DesignF df=factorySelection.getDataBase(DBtype);
		return df.findbyId(Id, user);
	}
	@DeleteMapping("/delete/{DBtype}/{Id}")
	public String deleteid(@RequestBody User user,@PathVariable String DBtype,@PathVariable Integer Id) {
		logger.info("inside deleteid method");
		 logger.info("Received deleteid request with user: " + user.toString() + " and DBtype: " + DBtype);
		DesignF df=factorySelection.getDataBase(DBtype);
		return df.deleteid(Id, user);
	}
	
	
}
