package com.packsendme.roadbrewa.vehicle.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.packsendme.roadbrewa.entity.VehicleType;

@Repository
public interface IVehicleType_Repository extends MongoRepository<VehicleType, String>{

	@Query("{'name_category' :  {$eq: ?0}}")
	VehicleType findVehicleTypeByName(String name_category);

	@Query("{'type_vehicle' :  {$eq: ?0}}")
	List<VehicleType> findVehicleTypeByType(String vehicle_type);

}
