package com.packsendme.roadbrewa.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadbrewa.entity.Vehicle;

@Repository
public interface IVehicle_Repository extends MongoRepository<Vehicle, String>{

	@Query("{id : { $ne : ?0}, vehicle : ?1}")
	Vehicle findVehicleByIdAndName(String id, String name);

	@Query("{'vehicle' : ?0}")
	Vehicle findVehicleByName(String name);
	
}
