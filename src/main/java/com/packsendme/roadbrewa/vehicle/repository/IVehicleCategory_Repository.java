package com.packsendme.roadbrewa.vehicle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadbrewa.entity.VehicleCategory;

@Repository
public interface IVehicleCategory_Repository extends MongoRepository<VehicleCategory, String>{

	@Query("{'type_vehicle' :  {$eq: ?0}}")
	VehicleCategory findVehicleByType(String type_vehicle);

}
