package com.packsendme.roadway.vehicle.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadway.commons.entity.VehicleType;

@Repository
public interface IVehicleType_Repository extends MongoRepository<VehicleType, String>{

	@Query("{'type_vehicle' :  {$eq: ?0}}")
	List<VehicleType> findVehicleTypeByType(String vehicle_type);

}
