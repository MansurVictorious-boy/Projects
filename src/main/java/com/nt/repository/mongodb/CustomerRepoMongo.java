package com.nt.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nt.mongodbentity.CustomerEntitymongo;
@Repository
public interface CustomerRepoMongo extends MongoRepository<CustomerEntitymongo, String> {

}
