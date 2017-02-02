package com.mongo.samplemongoapp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.mongo.samplemongoapp.domain.Users;


public interface UserRepository extends CrudRepository<Users, String>{
	
	
}
