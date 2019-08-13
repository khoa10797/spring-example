package com.dangkhoa.springexample.repositories;

import com.dangkhoa.springexample.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
}
