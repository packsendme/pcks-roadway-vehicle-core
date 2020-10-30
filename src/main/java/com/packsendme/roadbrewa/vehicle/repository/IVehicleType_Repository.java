package com.packsendme.roadbrewa.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadbrewa.entity.VehicleType;

@Repository
public interface IVehicleType_Repository extends MongoRepository<VehicleType, String>{

	@Query("{'type_vehicle' :  {$eq: ?0}}")
	VehicleType findVehicleTypeByName(String type_vehicle);

}
