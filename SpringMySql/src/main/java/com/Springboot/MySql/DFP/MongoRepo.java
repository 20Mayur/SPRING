package com.Springboot.MySql.DFP;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRepo extends MongoRepository<User,Integer> {

}
