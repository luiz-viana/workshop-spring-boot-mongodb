package com.luizviana.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.luizviana.workshopmongo.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


}
