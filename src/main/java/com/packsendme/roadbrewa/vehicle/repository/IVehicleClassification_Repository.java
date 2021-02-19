package com.packsendme.roadbrewa.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadbrewa.entity.VehicleClassification;

@Repository
public interface IVehicleClassification_Repository extends MongoRepository<VehicleClassification, String>{

	@Query("{'type_vehicle' :  {$eq: ?0}}")
	VehicleClassification findVehicleByType(String type_vehicle);

}
