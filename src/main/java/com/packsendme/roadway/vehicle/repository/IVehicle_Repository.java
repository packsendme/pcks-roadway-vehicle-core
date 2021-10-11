package com.packsendme.roadway.vehicle.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadway.commons.entity.Vehicle;

@Repository
public interface IVehicle_Repository extends MongoRepository<Vehicle, String>{

	@Query("{id : { $ne : ?0}, type_vehicle : ?1}")
	Vehicle findVehicleByIdAndName(String id, String name);

	@Query("{'category_vehicle' : ?0}")
	Vehicle findVehicleByName(String name);
	
	@Query("{'transport_type' : ?0}")
	List<Vehicle> findVehicleByTransportType(String name);
	
}
