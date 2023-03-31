package com.Springboot.MySql.Dao;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.Springboot.MySql.Entities.Notification;

public interface EmailRepository extends MongoRepository<Notification,String>{
}
