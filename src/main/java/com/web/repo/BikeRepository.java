package com.web.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.web.Model.storage.Bike;

@Repository
public interface BikeRepository extends MongoRepository<Bike, Long>{

}
