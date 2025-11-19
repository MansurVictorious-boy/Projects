package com.nt.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nt.mongodbentity.UserRegisterMongodb;
@Repository
public interface UserRegisterMongodbRepo extends MongoRepository<UserRegisterMongodb, String> {

}
