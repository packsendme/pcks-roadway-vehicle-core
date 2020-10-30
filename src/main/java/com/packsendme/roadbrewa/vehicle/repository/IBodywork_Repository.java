package com.packsendme.roadbrewa.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadbrewa.entity.Bodywork;

@Repository
public interface IBodywork_Repository extends MongoRepository<Bodywork, String>{

	@Query("{id : { $ne : ?0}, bodyWork : ?1}")
	Bodywork findBodyWorkByIdAndName(String id, String name);

	@Query("{'bodyWork' : ?0}")
	Bodywork findBodyWorkByName(String name);

}

