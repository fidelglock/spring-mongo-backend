package com.challenge.springmongobackend.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<Product,String> {
}
